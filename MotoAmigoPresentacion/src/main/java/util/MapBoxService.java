/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author xiomi
 */
public class MapBoxService {
    
     private static final String TOKEN = "pk.eyJ1IjoieGlvbWlhdS1kZXYiLCJhIjoiY21vaTQwbHh6MDJnNzJyb2h5MHR0aGc3NSJ9.LqqDXmSRLFyEw3us6rqejQ";

    // Convierte una dirección en coordenadas [lon, lat]
    public static double[] geocodificar(String direccion) throws Exception {
        String query = URLEncoder.encode(direccion + ", Culiacán, México", "UTF-8");
        String urlStr = "https://api.mapbox.com/geocoding/v5/mapbox.places/"
                + query + ".json?access_token=" + TOKEN + "&limit=1";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream is = conn.getInputStream();
        String json = new String(is.readAllBytes());
        is.close();

        JSONObject obj = new JSONObject(json);
        JSONArray features = obj.getJSONArray("features");

        if (features.isEmpty()) {
            throw new Exception("No se encontró la dirección: " + direccion);
        }

        JSONArray coords = features.getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONArray("coordinates");

        return new double[]{coords.getDouble(0), coords.getDouble(1)};
    }

    // Descarga la imagen del mapa con un pin en las coordenadas
    public static BufferedImage obtenerMapaEstatico(double lon, double lat, int ancho, int alto) throws Exception {
        String urlStr = "https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/"
                + "pin-s+ff6900(" + lon + "," + lat + ")/"
                + lon + "," + lat + ",14,0/"
                + ancho + "x" + alto
                + "?access_token=" + TOKEN;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream is = conn.getInputStream();
        BufferedImage imagen = ImageIO.read(is);
        is.close();

        return imagen;
    }
    
}
