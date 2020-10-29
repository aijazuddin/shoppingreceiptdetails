package com.msinc.shoppingreceipt.model;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
	private List<Item> items;
	private BigDecimal salesTax;
	private BigDecimal totalAmount;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
