package View;

import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
    }

}
