package util.guis;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Botón negro redondeado estilo MotoAmigo.
 * Uso: arrastrar desde palette o instanciar con new BotonNegro("Texto")
 */
public class BotonNegro extends JButton {

    private static final Color COLOR_NORMAL  = new Color(26, 26, 26);    // #1A1A1A
    private static final Color COLOR_HOVER   = new Color(50, 50, 50);
    private static final Color COLOR_PRESSED = new Color(10, 10, 10);
    private static final Color COLOR_TEXTO   = Color.WHITE;
    private static final int   RADIO         = 12;

    private Color colorActual = COLOR_NORMAL;

    public BotonNegro() {
        this("Botón");
    }

    public BotonNegro(String texto) {
        super(texto);
        configurar();
    }

    private void configurar() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(COLOR_TEXTO);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(350, 48));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                colorActual = COLOR_HOVER;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                colorActual = COLOR_NORMAL;
                repaint();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                colorActual = COLOR_PRESSED;
                repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                colorActual = COLOR_HOVER;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorActual);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), RADIO * 2, RADIO * 2);
        g2.dispose();
        super.paintComponent(g);
    }
}
