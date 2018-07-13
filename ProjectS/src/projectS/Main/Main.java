package projectS.Main;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	/**
	 * 
	 * Main-Methode startet das Programm.
	 *
	 */

	public static void main(String[] args) {

		Icon icon = new ImageIcon("Bilder/cat.jpg", "a pretty but meaningless splat");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		// Set the position of the text, relative to the icon:
		label1.setVerticalTextPosition(JLabel.BOTTOM);
		label1.setHorizontalTextPosition(JLabel.CENTER);

		// 1. Create the frame.
		JFrame frame = new JFrame("Project S - Version 0.0.01");

		// 2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Create components and put them in the frame.
		// ...create emptyLabel...
		frame.getContentPane().add(label1, BorderLayout.CENTER);

		// 4. Size the frame.
		frame.pack();

		// 5. Show it.
		frame.setVisible(true);
	}

}
