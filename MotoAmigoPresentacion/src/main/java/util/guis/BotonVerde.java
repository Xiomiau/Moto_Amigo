package util.guis;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Botón verde redondeado para acciones de aprobación.
 */
public class BotonVerde extends JButton {

    private static final Color COLOR_NORMAL  = new Color(40, 167, 69);   // #28A745
    private static final Color COLOR_HOVER   = new Color(30, 140, 55);
    private static final Color COLOR_PRESSED = new Color(20, 110, 40);
    private static final Color COLOR_TEXTO   = Color.WHITE;
    private static final int   RADIO         = 10;

    private Color colorActual = COLOR_NORMAL;

    public BotonVerde() {
        this("Aprobar");
    }

    public BotonVerde(String texto) {
        super(texto);
        configurar();
    }

    private void configurar() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(COLOR_TEXTO);
        setFont(new Font("Segoe UI", Font.BOLD, 13));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(100, 36));

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e)  { colorActual = COLOR_HOVER;   repaint(); }
            @Override public void mouseExited(MouseEvent e)   { colorActual = COLOR_NORMAL;  repaint(); }
            @Override public void mousePressed(MouseEvent e)  { colorActual = COLOR_PRESSED; repaint(); }
            @Override public void mouseReleased(MouseEvent e) { colorActual = COLOR_HOVER;   repaint(); }
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
