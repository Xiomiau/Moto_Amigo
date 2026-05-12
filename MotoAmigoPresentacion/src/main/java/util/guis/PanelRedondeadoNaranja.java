package util.guis;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Panel blanco redondeado con sombra sutil estilo MotoAmigo.
 * Úsalo como contenedor de formularios, tarjetas y secciones.
 */
public class PanelRedondeadoNaranja extends JPanel {

    private static final Color COLOR_FONDO  = new Color(255, 247, 237);
    private static final Color COLOR_SOMBRA = new Color(0, 0, 0, 20);
    private static final int   RADIO        = 16;

    public PanelRedondeadoNaranja() {
        setOpaque(false);
        setBackground(COLOR_FONDO);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Sombra
        for (int i = 4; i >= 1; i--) {
            g2.setColor(new Color(0, 0, 0, 5 * i));
            g2.fillRoundRect(i, i, getWidth() - i * 2, getHeight() - i * 2, RADIO * 2, RADIO * 2);
        }

        // Fondo blanco
        g2.setColor(COLOR_FONDO);
        g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, RADIO * 2, RADIO * 2);

        g2.dispose();
        super.paintComponent(g);
    }
}
