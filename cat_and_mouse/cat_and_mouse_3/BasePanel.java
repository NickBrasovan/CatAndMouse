package cat_and_mouse_3;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class BasePanel extends JPanel {
	

	public BasePanel(CardLayout cl) {
	
	this.setBackground(Color.BLACK);
	//this.setPreferredSize(new Dimension(800, 900));
	this.setVisible(true);
	this.setLayout(cl);
	
	
	}
}
