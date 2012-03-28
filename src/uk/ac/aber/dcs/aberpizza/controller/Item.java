package uk.ac.aber.dcs.aberpizza.controller;

public interface Item {
	public Double getPrice();
	public void setPrice(Double p);
	public String getDescription();
	public void setDescription(String d);
	public String getName();
	public void setName(String n);
}
