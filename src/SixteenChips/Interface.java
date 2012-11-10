/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SixteenChips;

/**
 *
 * @author Mamun
 */
import java.awt.event.*;
import java.awt.*;




public class Interface extends CustomJFrame {
  DisplayCanvas canvas;


  public Interface() {
    super("SholoGuti");
    Container container = getContentPane();

    canvas = new DisplayCanvas();
    container.add(canvas);
    addWindowListener(new WindowAdapter() {
            @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    canvas.setPreferredSize(new Dimension(805,650));


    this.setSize(800, 650);
    setResizable(false);
    setLocation(200,50);
    setVisible(true);
  }


 public static void main(String arg[]) {
        Interface aInterface = new Interface();
        aInterface.setAlwaysOnTop(true);
  }
}


