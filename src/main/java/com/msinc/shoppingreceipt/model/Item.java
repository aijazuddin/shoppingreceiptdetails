package com.msinc.shoppingreceipt.model;

import java.math.BigDecimal;

public class Item {
	private Double quantity;
	private BigDecimal price;
	private String name;
	private boolean imported;
	private BigDecimal salesTax;
	private BigDecimal importDuty;
	private double totalTax;
	private BigDecimal priceWithTax;

	public BigDecimal getPriceWithTax() {
		return priceWithTax;
	}

	public void setPriceWithTax(BigDecimal priceWithTax) {
		this.priceWithTax = priceWithTax;
	}

	public boolean isImported() {
		return imported;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public BigDecimal getImportDuty() {
		return importDuty;
	}

	public void setImportDuty(BigDecimal importDuty) {
		this.importDuty = importDuty;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// System.out.println("\nItem Details: "); String.valueOf(quantity.intValue()) + " " + name.trim() + ": " + getTotalPrice()
		if (getQuantity() != null && getPrice() != null && getName() != null) {
			 System.out.println(getQuantity().intValue()+ " "+getName()+ " : "+ getPriceWithTax());
		}
		return "";
	}
}