package View;

import Model.Estado;

import javax.swing.*;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransparentButton extends JButton {

    private Color bgColor;
    private boolean bestado;
    private Estado estado;

    public TransparentButton(Estado estado) {
        this.estado = estado;
        setup();
        this.bestado = true;
        this.setText("   ");
        bgColor = getColor(estado);
        setBackground(bgColor);
    }

    public Estado getEstado() {
        return estado;
    }

    public Color getColor() {
        return bgColor;
    }

    public Color getColor(Estado estado) {
        Color color = Color.BLACK;
        if (estado == Estado.activo) {
            color = Color.GREEN;
        } else if (estado == Estado.pausado) {
            color = Color.YELLOW;
        } else if (estado == Estado.terminado) {
            color = Color.GRAY;
        } else if (estado == Estado.expirado) {
            color = Color.RED;
        }
        return color;
    }

    public TransparentButton(String text) {
        this.bestado = false;
        bgColor = new Color(1,1,1,10);
        this.setText(text);
        setup();
    }

    private void setup() {
        Font font = new Font("Centhury Gothic",Font.PLAIN,16);
        setForeground(Color.white);
        setFont(font);
        setOpaque(false);

        setBackground(bgColor);

        setBorder(new LineBorder(Color.white, 1));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!bestado) {
                    setBackground(new Color(88, 2, 20, 220));
                }
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (!bestado) {
                    setBackground(bgColor);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
    }

}
