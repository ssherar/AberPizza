package uk.ac.aber.dcs.aberpizza.gui;

import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.data.*;

public class ReceiptDialog extends JDialog {
	private final JEditorPane receipt;
	private final String FORMAT = "<html><style>#body { width:450px;font-family:Tahoma; }p{text-align:center} p.none {text-align:left}table {width:100%; border:1px solid #000}</style><div id='body'><p>AberPizza</p> <p>Aberystwyth</p> <p class='none'>For: [:customerName]</p><br /><table><thead> <td width='45%'>Item Name</td> <td width='10%'>Quantity</td> <td width='25%'>Unitary Price</td> <td with='20%'>Price</td> </thead>[:items]<tr height='20px'></tr><tr> <td></td> <td></td> <td> Subtotal </td> <td> [:subtotal] </td> </tr><tr> <td></td> <td></td> <td> Discount </td> <td> [:discount] </td> </tr><tr> <td></td> <td></td> <td> Total </td> <td> [:total] </td> </tr><tr> <td></td> <td></td> <td> [:paymentoption]</td> <td>[:paid] </td> </tr></table><p>Thank you for your custom!</p></div></html>";
	private final String ITEMS = "<tr> <td> [:productName] </td> <td> [:quantity] </td> <td>£[:unitaryprice]</td>  <td>£[:itemprice] </td> </tr>";
	private Order order;
	
	public ReceiptDialog(Order o) {
		this(o ,null);
	}
	
	public ReceiptDialog(Order o, String method) {
		order = o;
		String itemRec = "";
		
		for(OrderItem i : order.getItems()) {
			String tmp = ITEMS;
			if(i.getOptions().size() > 0) {
				for(OrderItemOption oio: i.getOptions()) {
					BigDecimal price = Manager.round(i.getItem().getPrice().add(oio.getOption().getPrice()));
					int quantity = oio.getQuantity();
					tmp = tmp.replaceAll("\\[\\:productName\\]", i.getItem().getDescription() + " - " + oio.getOption().getSize());
					tmp = tmp.replaceAll("\\[\\:quantity\\]", ""+quantity);
					tmp = tmp.replaceAll("\\[\\:unitaryprice\\]", ""+price);
					tmp = tmp.replaceAll("\\[\\:itemprice\\]", ""+(Manager.round(price.multiply(new BigDecimal(quantity)))));
					itemRec += tmp + "\n";
				}
			} else {
				BigDecimal price = Manager.round(i.getItem().getPrice().multiply(new BigDecimal(i.getQuantity())));
				tmp = tmp.replaceAll("\\[\\:productName\\]", i.getItem().getDescription());
				tmp = tmp.replaceAll("\\[\\:quantity\\]", ""+i.getQuantity());
				tmp = tmp.replaceAll("\\[\\:unitaryprice\\]", ""+Manager.round(i.getItem().getPrice()));
				tmp = tmp.replaceAll("\\[\\:itemprice\\]", ""+price);
				itemRec += tmp + "\n";
			}
			
			
		}
		
		String recData = FORMAT.replaceAll("\\[\\:items\\]", itemRec);
		recData = recData.replaceAll("\\[\\:customerName\\]", o.getCustomerName());
		recData = recData.replaceAll("\\[\\:subtotal\\]", ""+Manager.round(o.getSubtotal()));
		recData = recData.replaceAll("\\[\\:paymentoption\\]", method);
		recData = recData.replaceAll("\\[\\:discount\\]", ""+order.getDiscount());
		recData = recData.replaceAll("\\[\\:paymentoption\\]", 
					((method == null) ? "Paid" : method)
				);
		recData = recData.replaceAll("\\[\\:total\\]", ""+order.getTotal());
		recData = recData.replaceAll("\\[\\:paid\\]", ""+order.getTotal());
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
