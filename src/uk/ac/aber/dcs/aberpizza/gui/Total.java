package uk.ac.aber.dcs.aberpizza.gui;

import javax.swing.*;
import java.awt.*;

public class Total extends JPanel {
	JLabel total;
	public Total() {
		super(null);
		setPreferredSize(new Dimension(0, 150));
		total = new JLabel("Total: £0.00");
		total.setPreferredSize(this.getPreferredSize());
		total.setHorizontalTextPosition(JLabel.CENTER);
		this.add(total);
	}
	
	@Override
    public void paintComponent(Graphics g) {
		int margin = 10;
		FontMetrics f= g.getFontMetrics();
		Dimension d = this.getSize();
		super.paintComponents(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(margin, margin, d.width - margin * 2, d.height - margin * 2, 5, 5);
		this.centerLabel(d, f);
	}
	
	private void centerLabel(Dimension d, FontMetrics f) {
		int width;
		width = (d.width - (f.stringWidth(total.getText()) / 2)) / 2;
		Rectangle r = new Rectangle(width, d.height / 8, 100, 100);
		total.setBounds(r);
	}
}
