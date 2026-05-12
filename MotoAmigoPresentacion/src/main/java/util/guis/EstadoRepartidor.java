package util.guis;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Badge (etiqueta de estado) con color según el estado del repartidor.
 * 
 * Uso:
 *   BadgeEstado badge = new BadgeEstado("PENDIENTE");
 *   BadgeEstado badge = new BadgeEstado("ACTIVO");
 *   BadgeEstado badge = new BadgeEstado("BLOQUEADO");
 *   BadgeEstado badge = new BadgeEstado("INACTIVO");
 */
public class EstadoRepartidor extends JLabel {

    // Colores fondo y texto por estado
    private static final Color FONDO_PENDIENTE  = new Color(255, 243, 205);
    private static final Color TEXTO_PENDIENTE  = new Color(133, 100, 4);

    private static final Color FONDO_ACTIVO     = new Color(212, 237, 218);
    private static final Color TEXTO_ACTIVO     = new Color(21, 87, 36);

    private static final Color FONDO_BLOQUEADO  = new Color(248, 215, 218);
    private static final Color TEXTO_BLOQUEADO  = new Color(114, 28, 36);

    private static final Color FONDO_INACTIVO   = new Color(230, 230, 230);
    private static final Color TEXTO_INACTIVO   = new Color(80, 80, 80);

    private Color colorFondo;
    private static final int RADIO = 8;

    public EstadoRepartidor() {
        this("PENDIENTE");
    }

    public EstadoRepartidor(String estado) {
        super(estado, SwingConstants.CENTER);
        setOpaque(false);
        setPreferredSize(new Dimension(100, 26));
        setFont(new Font("Segoe UI", Font.BOLD, 11));
        aplicarEstado(estado);
    }

    public void aplicarEstado(String estado) {
        switch (estado.toUpperCase()) {
            case "ACTIVO":
                colorFondo = FONDO_ACTIVO;
                setForeground(TEXTO_ACTIVO);
                break;
            case "BLOQUEADO":
                colorFondo = FONDO_BLOQUEADO;
                setForeground(TEXTO_BLOQUEADO);
                break;
            case "INACTIVO":
                colorFondo = FONDO_INACTIVO;
                setForeground(TEXTO_INACTIVO);
                break;
            default: // PENDIENTE
                colorFondo = FONDO_PENDIENTE;
                setForeground(TEXTO_PENDIENTE);
                break;
        }
        setText(estado.toUpperCase());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), RADIO * 2, RADIO * 2);
        g2.dispose();
        super.paintComponent(g);
    }
}
