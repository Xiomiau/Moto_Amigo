package util.guis;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class PanelTablaRepartidores extends JPanel {

    private static final Color COLOR_HEADER = new Color(245, 245, 245);
    private static final Color COLOR_BORDE = new Color(220, 220, 220);
    public static final int[] ANCHOS_COLUMNAS = {30, 155, 155, 100, 100, 90, 190, 150};
    private JPanel panelFilas;

    public PanelTablaRepartidores() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(crearEncabezado(), BorderLayout.NORTH);

        panelFilas = new JPanel();
        panelFilas.setLayout(new BoxLayout(panelFilas, BoxLayout.Y_AXIS));
        panelFilas.setBackground(Color.WHITE);
        add(panelFilas, BorderLayout.CENTER);
    }

    // ── Encabezado de columnas ───────────────────────────────────────────────
    private JPanel crearEncabezado() {
        JPanel header = new JPanel(new GridBagLayout());
    header.setBackground(COLOR_HEADER);
    header.setBorder(new MatteBorder(0, 0, 1, 0, COLOR_BORDE));
    header.setPreferredSize(new Dimension(0, 36));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets  = new Insets(0, 8, 0, 8);
    gbc.fill    = GridBagConstraints.HORIZONTAL;
    gbc.gridy   = 0;
    gbc.weighty = 1.0;

    String[] columnas = {"#", "Nombre Completo","Estado", "Fecha Registro", "Acciones"};

    for (int i = 0; i < columnas.length; i++) {
        gbc.gridx   = i;
        gbc.weightx = (i == columnas.length - 1) ? 1.0 : 0.0;

        JLabel lbl = new JLabel(columnas[i]);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(new Color(80, 80, 80));
        lbl.setPreferredSize(new Dimension(ANCHOS_COLUMNAS[i], 36));
        lbl.setMinimumSize (new Dimension(ANCHOS_COLUMNAS[i], 36));
        if (i == columnas.length - 1) lbl.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(lbl, gbc);
    }
    return header;
    }

    /**
     * Agrega una fila a la tabla. Los listeners de los botones se asignan aquí.
     *
     * Ejemplo de uso: panelTabla.agregarFila(dto, fila -> { fila.onVer(e ->
     * abrirDetalle(dto)); fila.onAprobar(e -> aprobar(dto)); fila.onRechazar(e
     * -> rechazar(dto)); });
     */
    public void agregarFila(int numero, String nombre, String email,
            String telefono, String transporte,
            String estado, String fecha,
            FilaCallback callback) {

        FilaRepartidor fila = new FilaRepartidor(numero, nombre, email,
                telefono, transporte,
                estado, fecha);
        if (callback != null) {
            callback.configurar(fila);
        }
        panelFilas.add(fila);
        panelFilas.revalidate();
        panelFilas.repaint();
    }

    /**
     * Limpia todas las filas (útil para recargar la tabla)
     */
    public void limpiar() {
        panelFilas.removeAll();
        panelFilas.revalidate();
        panelFilas.repaint();
    }

    // ── Interfaz funcional para configurar listeners por fila ────────────────
    public interface FilaCallback {

        void configurar(FilaRepartidor fila);
    }
}
