package util.guis;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TarjetaTransporte extends JPanel {

    private static final Color COLOR_NORMAL = Color.WHITE;
    private static final Color COLOR_SELECCIONADO = new Color(255, 247, 237);
    private static final Color COLOR_BORDE_NORMAL = new Color(220, 220, 220);
    private static final Color COLOR_BORDE_SELEC = new Color(255, 107, 0);
    private static final int RADIO = 12;

    // ── Propiedades editables desde el GUI Builder ──────────────────────────
    private String titulo = "Título";
    private String descripcion = "Descripción";
    private String valor = "VALOR";
    // ────────────────────────────────────────────────────────────────────────

    private boolean seleccionada = false;
    private Color colorFondo = COLOR_NORMAL;
    private Color colorBorde = COLOR_BORDE_NORMAL;

    private JLabel lblTitulo;
    private JLabel lblDescripcion;

    private GrupoTarjetas grupo;

    public TarjetaTransporte() {
        initComponentes();
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

    private void initComponentes() {
        setOpaque(false);
        setPreferredSize(new Dimension(150, 80));
        setLayout(new BorderLayout(0, 4));
        setBorder(new EmptyBorder(12, 12, 12, 12));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(new Color(30, 30, 30));

        lblDescripcion = new JLabel("<html><body style='width:120px'>" + descripcion + "</body></html>");
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDescripcion.setForeground(new Color(100, 100, 100));

        add(lblTitulo, BorderLayout.NORTH);
        add(lblDescripcion, BorderLayout.CENTER);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        if (lblTitulo != null) {
            lblTitulo.setText(titulo);
        }
        repaint();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        if (lblDescripcion != null) {
            lblDescripcion.setText("<html><body style='width:120px'>" + descripcion + "</body></html>");
        }
        repaint();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

   
    public void registrarEnGrupo(GrupoTarjetas grupo, JPanel panelAOcultar) {
        this.grupo = grupo;
        grupo.registrar(this, panelAOcultar);
    }

    // ── Lógica de selección ──────────────────────────────────────────────────
    public void seleccionar() {
        seleccionada = true;
        colorFondo = COLOR_SELECCIONADO;
        colorBorde = COLOR_BORDE_SELEC;
        repaint();
        if (grupo != null) {
            grupo.notificarSeleccion(this);
        }
    }

    public void deseleccionar() {
        seleccionada = false;
        colorFondo = COLOR_NORMAL;
        colorBorde = COLOR_BORDE_NORMAL;
        repaint();
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    // ── Dibujo ───────────────────────────────────────────────────────────────
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

    // ════════════════════════════════════════════════════════════════════════
    // Clase estática interna — coordina selección exclusiva y visibilidad
    // ════════════════════════════════════════════════════════════════════════
    public static class GrupoTarjetas {

        private final List<TarjetaTransporte> tarjetas = new ArrayList<>();
        private final List<JPanel> panelsOcultar = new ArrayList<>();

        public void registrar(TarjetaTransporte tarjeta, JPanel panelAOcultar) {
            tarjetas.add(tarjeta);
            panelsOcultar.add(panelAOcultar);
        }

        public void notificarSeleccion(TarjetaTransporte seleccionada) {
            for (int i = 0; i < tarjetas.size(); i++) {
                TarjetaTransporte t = tarjetas.get(i);
                JPanel panel = panelsOcultar.get(i);

                if (t == seleccionada) {
                    if (panel != null) {
                        panel.setVisible(false);
                        panel.revalidate();
                        panel.repaint();
                    }
                } else {
                    t.deseleccionar();
                    if (panel != null) {
                        panel.setVisible(true);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            }
        }

        public String getValorSeleccionado() {
            for (TarjetaTransporte t : tarjetas) {
                if (t.isSeleccionada()) {
                    return t.getValor();
                }
            }
            return null;
        }

        public boolean haySeleccion() {
            return getValorSeleccionado() != null;
        }
    }
}
