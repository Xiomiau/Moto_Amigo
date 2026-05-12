package util.guis;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Área de carga de archivo con borde punteado estilo MotoAmigo.
 *
 * Uso:
 *   AreaCargaArchivo ine = new AreaCargaArchivo("INE");
 *   byte[] datos = ine.getArchivoBytes();
 */
public class AreaCargaArchivo extends JPanel {

    private static final Color COLOR_BORDE_NORMAL  = new Color(200, 200, 200);
    private static final Color COLOR_BORDE_HOVER   = new Color(255, 107, 0);
    private static final Color COLOR_FONDO_NORMAL  = Color.WHITE;
    private static final Color COLOR_FONDO_CARGADO = new Color(245, 255, 245);
    private static final Color COLOR_ICONO         = new Color(180, 180, 180);
    private static final Color COLOR_TEXTO         = new Color(120, 120, 120);
    private static final int   RADIO               = 10;

    private Color colorBorde = COLOR_BORDE_NORMAL;
    private Color colorFondo = COLOR_FONDO_NORMAL;

    private byte[] archivoBytes;
    private final JLabel labelEstado;
    private final JLabel labelSubtitulo;
    private boolean archivoCargado = false;

    public AreaCargaArchivo() {
        this("Archivo");
    }

    public AreaCargaArchivo(String nombreArchivo) {
        setOpaque(false);
        setPreferredSize(new Dimension(340, 64));
        setLayout(new BorderLayout(10, 0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Ícono documento
        JLabel icono = new JLabel("\uD83D\uDCC4"); // 📄
        icono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        icono.setForeground(COLOR_ICONO);
        icono.setBorder(new EmptyBorder(0, 16, 0, 0));

        // Textos
        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new GridLayout(2, 1));

        labelEstado = new JLabel("Haz clic para cargar " + nombreArchivo);
        labelEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelEstado.setForeground(new Color(80, 80, 80));

        labelSubtitulo = new JLabel("PDF, JPG o PNG");
        labelSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        labelSubtitulo.setForeground(COLOR_TEXTO);

        textos.add(labelEstado);
        textos.add(labelSubtitulo);

        add(icono, BorderLayout.WEST);
        add(textos, BorderLayout.CENTER);

        // Hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!archivoCargado) colorBorde = COLOR_BORDE_HOVER;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!archivoCargado) colorBorde = COLOR_BORDE_NORMAL;
                repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirSelectorArchivo();
            }
        });
    }

    private void abrirSelectorArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Imágenes y PDF", "jpg", "jpeg", "png", "pdf"));
        int resultado = selector.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                archivoBytes = java.nio.file.Files.readAllBytes(archivo.toPath());
                archivoCargado = true;
                colorFondo = COLOR_FONDO_CARGADO;
                colorBorde = new Color(40, 167, 69); // verde al cargar
                labelEstado.setText("Archivo cargado");
                labelEstado.setForeground(new Color(21, 87, 36));
                labelSubtitulo.setText(archivo.getName());
                labelSubtitulo.setForeground(new Color(80, 80, 80));
                repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Retorna los bytes del archivo cargado, o null si no se cargó */
    public byte[] getArchivoBytes() {
        return archivoBytes;
    }

    /** Indica si ya se cargó un archivo */
    public boolean isArchivoCargado() {
        return archivoCargado;
    }

    /** Resetea el componente */
    public void limpiar() {
        archivoBytes = null;
        archivoCargado = false;
        colorFondo = COLOR_FONDO_NORMAL;
        colorBorde = COLOR_BORDE_NORMAL;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo
        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), RADIO * 2, RADIO * 2);

        // Borde punteado
        g2.setColor(colorBorde);
        float[] dash = {6f, 4f};
        g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, dash, 0f));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, RADIO * 2, RADIO * 2);

        g2.dispose();
        super.paintComponent(g);
    }
}
