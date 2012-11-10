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
import javax.swing.*;
/*
 */



/**
 * @author mamun
 */
public class CustomJFrame extends JFrame {
	public CustomJFrame(String s) {
                super(s);
		initComponents();
	}



	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem1_1 = new JMenuItem();
		menuItem1_2 = new JMenuItem();
		menuItem1_3 = new JMenuItem();
		menu2 = new JMenu();
		menuItem2_1 = new JMenuItem();
		menuItem2_2 = new JMenuItem();

		//======== this ========
		setName("MainFrame");
		//Container contentPane = getContentPane();
		//contentPane.setLayout(null);

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("Options");

				//---- menuItem1_1 ----
				menuItem1_1.setText("New Game");
				menuItem1_1.addMouseListener(new MouseAdapter() {


				});
				menu1.add(menuItem1_1);

				//---- menuItem1_2 ----
				menuItem1_2.setText("Open Game");
				menuItem1_2.addMouseListener(new MouseAdapter() {

				});
				menu1.add(menuItem1_2);

				//---- menuItem1_3 ----
				menuItem1_3.setText("Save Game");
				menuItem1_3.addMouseListener(new MouseAdapter() {

				});
				menu1.add(menuItem1_3);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("Help");

				//---- menuItem2_1 ----
				menuItem2_1.setText("About US");
				menuItem2_1.addMouseListener(new MouseAdapter() {

				});
				menu2.add(menuItem2_1);

				//---- menuItem2_2 ----
				menuItem2_2.setText("Version");
				menuItem2_2.addMouseListener(new MouseAdapter() {

				});
				menu2.add(menuItem2_2);
			}
			menuBar1.add(menu2);
		}
		setJMenuBar(menuBar1);

		//contentPane.setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem1_1;
	private JMenuItem menuItem1_2;
	private JMenuItem menuItem1_3;
	private JMenu menu2;
	private JMenuItem menuItem2_1;
	private JMenuItem menuItem2_2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
