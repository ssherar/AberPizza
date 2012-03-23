package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AberPizza");
		setSize(new Dimension(1024,800));
		setVisible(true);

		this.setJMenuBar(new MenuBar());
	}
}
