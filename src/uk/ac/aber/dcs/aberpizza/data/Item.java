package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;

public interface Item {
	public BigDecimal getPrice();
	public void setPrice(BigDecimal d);
	public String getDescription();
	public void setDescription(String d);
	public String getName();
	public void setName(String n);
}
