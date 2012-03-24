package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.*;
import java.awt.event.*;

public class Manager {
	private MainFrame window;
	private JTable items;
	
	public Manager() {
		window = new MainFrame();
		items = window.getItems();
	}
}
