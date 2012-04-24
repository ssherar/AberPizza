package uk.ac.aber.dcs.aberpizza.gui;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

public class ReceiptDialog extends JDialog {
	private final JEditorPane receipt;
	private final String FORMAT = "<html><style>#body { width:450px;font-family:Tahoma; }p{text-align:center} p.none {text-align:left}table {width:100%; border:1px solid #000}</style><div id='body'><p>AberPizza</p> <p>Aberystwyth</p> <p class='none'>For: [:customerName]</p><br /><table><thead> <td width='45%'>Item Name</td> <td width='10%'>Quantity</td> <td width='25%'>Unitary Price</td> <td with='20%'>Price</td> </thead>[:items]<tr height='20px'></tr><tr> <td></td> <td></td> <td> Subtotal </td> <td> [:subtotal] </td> </tr><tr> <td></td> <td></td> <td> Discount </td> <td> [:discount] </td> </tr><tr> <td></td> <td></td> <td> Total </td> <td> [:total] </td> </tr><tr> <td></td> <td></td> <td> [:paymentoption]</td> <td>[:paid] </td> </tr></table><p>Thank you for your custom!</p></div></html>";
	private final String ITEMS = "<tr> <td> [:productName] </td> <td> [:quantity] </td> <td>£[:unitaryprice]</td>  <td>£[:itemprice] </td> </tr>";
	
	public ReceiptDialog() {
		String recData = FORMAT.replaceAll("\\[\\:items\\]", ITEMS);
		receipt = new JEditorPane();
		receipt.setEditorKit(new HTMLEditorKit());
		receipt.setText(recData);
		receipt.setEditable(false);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setSize(new java.awt.Dimension(receipt.getSize().width, receipt.getPreferredSize().height+50));
			}
		});
		this.add(receipt);
		this.setVisible(true);
		this.pack();
		this.validate();
	}
}
