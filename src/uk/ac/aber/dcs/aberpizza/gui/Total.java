package uk.ac.aber.dcs.aberpizza.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DateFormat;

public class Total extends JPanel {
	private final String totalString = "Total: £%s (Discount : %s)";
	private JLabel total;
	public Total() {
		super(null);
		setPreferredSize(new Dimension(0, 150));
		total = new JLabel(String.format(totalString, new BigDecimal(0.00), new BigDecimal(0.00)));
		total.setPreferredSize(this.getPreferredSize());
		total.setHorizontalTextPosition(JLabel.CENTER);
		this.add(total);
	}
	
	@Override
    public void paintComponent(Graphics g) {
		int margin = 10;
		FontMetrics f= g.getFontMetrics();
		Dimension d = this.getSize();
		total.setPreferredSize(new Dimension(f.stringWidth(total.getText()), 0));
		super.paintComponents(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(margin, margin, d.width - margin * 2, d.height - margin * 2, 5, 5);
		this.centerLabel(d, f);
		this.getParent().doLayout();
	}
	
	private void centerLabel(Dimension d, FontMetrics f) {
		int width;
		width = (d.width - (f.stringWidth(total.getText()) / 2)) / 2;
		Rectangle r = new Rectangle(width, d.height / 8, 400, 100);
		total.setBounds(r);
	}
	
	public BigDecimal getValue() {
		return new BigDecimal(total.getText().substring(total.getText().lastIndexOf("£") + 1));
	}
	
	public void setValue(BigDecimal v, BigDecimal discount) {
		total.setText(String.format(totalString, v, discount));
	}
}
