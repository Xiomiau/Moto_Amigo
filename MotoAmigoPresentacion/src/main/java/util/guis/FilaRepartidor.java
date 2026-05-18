package util.guis;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class FilaRepartidor extends JPanel {

    private static final Color COLOR_FONDO = Color.WHITE;
    private static final Color COLOR_BORDE = new Color(230, 230, 230);
    private static final Color COLOR_TEXTO = new Color(40, 40, 40);
    private static final Color COLOR_SUBTEXTO = new Color(120, 120, 120);

    // Colores botones
    private static final Color COLOR_VER = new Color(100, 100, 100);
    private static final Color COLOR_APROBAR = new Color(34, 197, 94);
    private static final Color COLOR_RECHAZAR = new Color(239, 68, 68);

    // Colores estado
    private static final Color COLOR_PENDIENTE = new Color(251, 191, 36);
    private static final Color COLOR_ACTIVO = new Color(34, 197, 94);
    private static final Color COLOR_INACTIVO = new Color(156, 163, 175);
    private static final Color COLOR_RECHAZADO = new Color(239, 68, 68);

    private final int numero;
    private final String nombreCompleto;
    private final String email;
    private final String telefono;
    private final String transporte;
    private final String estado;
    private final String fechaRegistro;
    int[] anchos = PanelTablaRepartidores.ANCHOS_COLUMNAS;

    private JButton btnVer;
    private JButton btnAprobar;
    private JButton btnRechazar;

    public FilaRepartidor(int numero, String nombreCompleto, String email,
            String telefono, String transporte,
            String estado, String fechaRegistro) {
        this.numero = numero;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.transporte = transporte;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        initComponentes();
    }

    private void initComponentes() {
        setBackground(COLOR_FONDO);
        setLayout(new GridBagLayout());
        setBorder(new MatteBorder(0, 0, 1, 0, COLOR_BORDE));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        setPreferredSize(new Dimension(0, 55));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 8, 0, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        // Columna 0 — #
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        add(celda(labelTexto(String.valueOf(numero), COLOR_SUBTEXTO, Font.PLAIN, 12), 0), gbc);

        // Columna 1 — Nombre completo
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        add(celda(labelTexto(nombreCompleto, COLOR_TEXTO, Font.BOLD, 12), 1), gbc);

        // Columna 2 — Estado
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        add(celda(labelTexto(estado, COLOR_SUBTEXTO, Font.PLAIN, 11), 2), gbc);


        // Columna 3 — Fecha registro
        gbc.gridx = 6;
        gbc.weightx = 0.0;
        add(celda(labelTexto(fechaRegistro, COLOR_SUBTEXTO, Font.PLAIN, 11), 6), gbc);

        // Columna 4 — Acciones (última: absorbe espacio sobrante)
        gbc.gridx = 7;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(crearPanelAcciones(), gbc);
    }

    /**
     * Envuelve un componente en un panel de ancho fijo para alinear columnas.
     */
    private JPanel celda(JComponent contenido, int columna) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setPreferredSize(new Dimension(anchos[columna], 55));
        p.setMinimumSize(new Dimension(anchos[columna], 55));
        p.setMaximumSize(new Dimension(anchos[columna], 55));
        p.add(contenido, BorderLayout.WEST);
        return p;
    }

    // ── Helpers de UI ────────────────────────────────────────────────────────
    private JLabel labelTexto(String texto, Color color, int estilo, int size) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", estilo, size));
        lbl.setForeground(color);
        return lbl;
    }

    private JLabel crearBadgeEstado(String estado) {
        JLabel badge = new JLabel(estado, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setOpaque(false);
        badge.setFont(new Font("Segoe UI", Font.BOLD, 10));
        badge.setForeground(Color.WHITE);
        badge.setBorder(new EmptyBorder(3, 10, 3, 10));

        switch (estado.toUpperCase()) {
            case "PENDIENTE" ->
                badge.setBackground(COLOR_PENDIENTE);
            case "ACTIVO" ->
                badge.setBackground(COLOR_ACTIVO);
            case "INACTIVO" ->
                badge.setBackground(COLOR_INACTIVO);
            case "RECHAZADO" ->
                badge.setBackground(COLOR_RECHAZADO);
            default ->
                badge.setBackground(COLOR_INACTIVO);
        }
        return badge;
    }

    private JPanel crearPanelAcciones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        panel.setOpaque(false);

        btnVer = crearBoton("Ver", COLOR_VER);
        btnAprobar = crearBoton("Aprobar", COLOR_APROBAR);
        btnRechazar = crearBoton("Rechazar", COLOR_RECHAZAR);

        panel.add(btnVer);

        // Solo PENDIENTE muestra Aprobar y Rechazar
        if ("PENDIENTE".equalsIgnoreCase(estado)) {
            panel.add(btnAprobar);
            panel.add(btnRechazar);
        }

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(80, 24));
        return btn;
    }

    // ── Listeners públicos ───────────────────────────────────────────────────
    public void onVer(ActionListener listener) {
        btnVer.addActionListener(listener);
    }

    public void onAprobar(ActionListener listener) {
        if (btnAprobar != null) {
            btnAprobar.addActionListener(listener);
        }
    }

    public void onRechazar(ActionListener listener) {
        if (btnRechazar != null) {
            btnRechazar.addActionListener(listener);
        }
    }
}
