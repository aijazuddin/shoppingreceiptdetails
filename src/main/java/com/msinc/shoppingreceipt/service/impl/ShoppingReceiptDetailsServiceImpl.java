package com.msinc.shoppingreceipt.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msinc.shoppingreceipt.constant.ShoppingReceiptDetailsConstants;
import com.msinc.shoppingreceipt.model.Item;
import com.msinc.shoppingreceipt.model.Receipt;
import com.msinc.shoppingreceipt.util.ReceiptUtil;

/**
 * @author Aijazuddin
 *
 */
public class ShoppingReceiptDetailsServiceImpl {

	public static final Logger logger = LoggerFactory.getLogger(ShoppingReceiptDetailsServiceImpl.class.getName());

	public Receipt getShoppingReceiptDetails(File file) {
		//logger.info( "{} Received", ShoppingReceiptDetailsConstants.SHOPPING_RECEIPT_REQUEST);
		Receipt receipt = new Receipt();
		receipt.setItems(ReceiptUtil.getItems(file));
		BigDecimal totalAmount = new BigDecimal("0.00");
		BigDecimal totalTax = new BigDecimal("0.00");
		for (Item item : receipt.getItems()) {
			calculateTaxes(item);
			totalAmount = totalAmount
					.add(new BigDecimal(item.getQuantity().doubleValue()).multiply(item.getPriceWithTax()));
			totalTax = totalTax.add(new BigDecimal(item.getTotalTax()));
		}
		receipt.setTotalAmount(totalAmount.setScale(2, RoundingMode.HALF_UP));
		receipt.setSalesTax(totalTax.setScale(2, RoundingMode.HALF_UP));
		ReceiptUtil.print(receipt);
		return receipt;
	}
	
	public void calculateTaxes(Item item) {
		Set<String> salesTaxExemptionItems = new HashSet<>();
		salesTaxExemptionItems.addAll(ShoppingReceiptDetailsConstants.books);
		salesTaxExemptionItems.addAll(ShoppingReceiptDetailsConstants.food);
		salesTaxExemptionItems.addAll(ShoppingReceiptDetailsConstants.medicine);
		double taxPercentage = 0.00;
		if (item.isImported()) {
			taxPercentage = ShoppingReceiptDetailsConstants.IMPORT_DUTY_TAX;
		}
		if (!salesTaxExemptionItems.stream().filter(st -> item.getName().toUpperCase().contains(st)).findFirst().isPresent()) {
			taxPercentage += ShoppingReceiptDetailsConstants.SALES_TAX;
		}
		item.setTotalTax(ReceiptUtil.roundTax(item.getPrice().doubleValue() * taxPercentage));
		item.setPriceWithTax(
				((new BigDecimal(item.getTotalTax())).add(item.getPrice())).setScale(2, RoundingMode.HALF_UP));
		// System.out.println("item price-->"+item.getPriceWithTax());
	}
}
