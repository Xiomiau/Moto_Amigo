package util.guis;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Tarjeta seleccionable para tipo de transporte estilo MotoAmigo.
 *
 * Uso:
 *   TarjetaTransporte moto = new TarjetaTransporte("🏍 Moto / Auto", "Requiere licencia, circulación y seguro.", "MOTO_AUTO");
 *   TarjetaTransporte bici = new TarjetaTransporte("🚲 Bicicleta", "No requiere licencia ni circulación.", "BICICLETA");
 *
 *   // Vincularlas para que solo una quede seleccionada:
 *   moto.vincularCon(bici);
 *   bici.vincularCon(moto);
 *
 *   // Obtener el valor seleccionado:
 *   String tipo = moto.isSeleccionada() ? moto.getValor() : bici.getValor();
 */
public class TarjetaTransporte extends JPanel {

    private static final Color COLOR_NORMAL         = Color.WHITE;
    private static final Color COLOR_SELECCIONADO   = new Color(255, 247, 237);  
    private static final Color COLOR_BORDE_NORMAL   = new Color(220, 220, 220);
    private static final Color COLOR_BORDE_SELEC    = new Color(255, 107, 0);
    private static final int   RADIO                = 12;

    private boolean seleccionada = false;
    private Color colorFondo = COLOR_NORMAL;
    private Color colorBorde = COLOR_BORDE_NORMAL;

    private final String valor;
    private TarjetaTransporte pareja;

    public TarjetaTransporte() {
        this(" Moto / Auto", "Requiere licencia, circulación y seguro.", "MOTO_AUTO");
    }

    public TarjetaTransporte(String titulo, String descripcion, String valor) {
        this.valor = valor;
        setOpaque(false);
        setPreferredSize(new Dimension(160, 90));
        setLayout(new BorderLayout(0, 4));
        setBorder(new EmptyBorder(12, 12, 12, 12));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(new Color(30, 30, 30));

        JLabel lblDescripcion = new JLabel("<html><body style='width:120px'>" + descripcion + "</body></html>");
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDescripcion.setForeground(new Color(100, 100, 100));

        add(lblTitulo, BorderLayout.NORTH);
        add(lblDescripcion, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionar();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!seleccionada) {
                    colorBorde = new Color(255, 150, 50);
                    repaint();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!seleccionada) {
                    colorBorde = COLOR_BORDE_NORMAL;
                    repaint();
                }
            }
        });
    }

    /** Vincula esta tarjeta con su pareja para selección exclusiva */
    public void vincularCon(TarjetaTransporte pareja) {
        this.pareja = pareja;
    }

    /** Selecciona esta tarjeta y deselecciona la pareja */
    public void seleccionar() {
        seleccionada = true;
        colorFondo = COLOR_SELECCIONADO;
        colorBorde = COLOR_BORDE_SELEC;
        repaint();
        if (pareja != null) {
            pareja.deseleccionar();
        }
    }

    /** Deselecciona esta tarjeta */
    public void deseleccionar() {
        seleccionada = false;
        colorFondo = COLOR_NORMAL;
        colorBorde = COLOR_BORDE_NORMAL;
        repaint();
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public String getValor() {
        return valor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), RADIO * 2, RADIO * 2);
        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(seleccionada ? 2f : 1.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, RADIO * 2, RADIO * 2);
        g2.dispose();
        super.paintComponent(g);
    }
}
