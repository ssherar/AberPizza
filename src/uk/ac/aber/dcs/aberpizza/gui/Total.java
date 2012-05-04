package uk.ac.aber.dcs.aberpizza.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DateFormat;

/**
 * The total pane has one soul purpose, and that is to display the total with the
 * discount alongside
 * @author Samuel B Sherar (sbs1)
 *
 */
public class Total extends JPanel {
	
	/** The total string. */
	private final String totalString = "Total: £%s (Discount : %s)";
	
	/** The total. */
	private JLabel total;
	
	/**
	 * Instantiates a new total panel
	 */
	public Total() {
		super(null);
		setPreferredSize(new Dimension(0, 150));
		total = new JLabel(String.format(totalString, new BigDecimal(0.00), new BigDecimal(0.00)));
		total.setPreferredSize(this.getPreferredSize());
		total.setHorizontalTextPosition(JLabel.CENTER);
		this.add(total);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
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
	
	/**
	 * Centers the label.
	 *
	 * @param d the dimension
	 * @param f the fontmetrics
	 */
	private void centerLabel(Dimension d, FontMetrics f) {
		int width;
		width = (d.width - (f.stringWidth(total.getText()) / 2)) / 2;
		Rectangle r = new Rectangle(width, d.height / 8, 400, 100);
		total.setBounds(r);
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public BigDecimal getValue() {
		return new BigDecimal(total.getText().substring(total.getText().lastIndexOf("£") + 1));
	}
	
	/**
	 * Sets the value.
	 *
	 * @param v the v
	 * @param discount the discount
	 */
	public void setValue(BigDecimal v, BigDecimal discount) {
		total.setText(String.format(totalString, v, discount));
	}
}
