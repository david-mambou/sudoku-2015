package sudoku2015;


import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

 class RButton extends JButton {
  
  public RButton(ImageIcon img) {
    Dimension size = getPreferredSize();
    size.width = size.height = Math.max(size.width,size.height);
    setPreferredSize(size);
    setContentAreaFilled(false);
    this.setIcon(img);
  }

  protected void paintComponent(Graphics g) {
    if (getModel().isArmed()) {
      g.setColor(Color.lightGray);
    }
    else {
      g.setColor(getBackground());
    }
    g.fillOval(0, 0, getSize().width - 1,
               getSize().height - 1);
    super.paintComponent(g);
  }

  protected void paintBorder(Graphics g) {
    g.setColor(getForeground());
    g.drawOval(0, 0, getSize().width - 1,
               getSize().height - 1);
  }
}
 