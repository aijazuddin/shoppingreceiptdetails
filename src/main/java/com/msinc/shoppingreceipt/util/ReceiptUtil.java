package com.msinc.shoppingreceipt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msinc.shoppingreceipt.constant.ShoppingReceiptDetailsConstants;
import com.msinc.shoppingreceipt.model.Item;
import com.msinc.shoppingreceipt.model.Receipt;
import com.msinc.shoppingreceipt.service.impl.ShoppingReceiptDetailsServiceImpl;

public class ReceiptUtil {
	public static final Logger logger = LoggerFactory.getLogger(ShoppingReceiptDetailsServiceImpl.class.getName());
	

	/*public static void calculateBill(Receipt receipt) {
		
	}*/

	/*public static void calculateTaxes(Item item) {
		Set<String> salesTaxExemptionItems = new HashSet<>();
		salesTaxExemptionItems.addAll(books);
		salesTaxExemptionItems.addAll(food);
		salesTaxExemptionItems.addAll(medicine);
		double taxPercentage = 0.00;
		if (item.isImported()) {
			taxPercentage = ShoppingReceiptDetailsConstants.IMPORT_DUTY_TAX;
		}
		if (!salesTaxExemptionItems.stream().filter(st -> item.getName().toUpperCase().contains(st)).findFirst().isPresent()) {
			taxPercentage += ShoppingReceiptDetailsConstants.SALES_TAX;
		}
		item.setTotalTax(roundTax(item.getPrice().doubleValue() * taxPercentage));
		item.setPriceWithTax(
				((new BigDecimal(item.getTotalTax())).add(item.getPrice())).setScale(2, RoundingMode.HALF_UP));
		// System.out.println("item price-->"+item.getPriceWithTax());
	}*/

	public static double roundTax(double number) {
		return Math.ceil(number * 20) / 20;
	}

	public static void print(Receipt receipt) {
		receipt.getItems().stream().forEach(item -> item.toString());
		System.out.println("Sales Tax: " + receipt.getSalesTax());
		System.out.println("Total Tax: " + receipt.getTotalAmount());
	}

	public static List<Item> getItems(File file) {
		BufferedReader reader = null;
		Item item = null;
		List<Item> items = null;
		String line = null;
		String[] lineSpitBySpace;
		int lineSpitBySpaceLength = 0;
		StringJoiner name = null;
		try {
			item = new Item();
			items = new ArrayList<>();
			reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			line = reader.readLine();
			while (line != null) {
				name = new StringJoiner(" ");
				// System.out.println(line);
				lineSpitBySpace = line.split(" ");
				lineSpitBySpaceLength = lineSpitBySpace.length;
				item = new Item();
				item.setQuantity(Double.parseDouble(lineSpitBySpace[0]));
				item.setPrice(new BigDecimal(lineSpitBySpace[lineSpitBySpaceLength - 1]));
				for (int i = 1; i < lineSpitBySpaceLength - 2; i++) {
					name.add(lineSpitBySpace[i]);
				}
				item.setName(name.toString());
				if (item.getName().toUpperCase().contains(ShoppingReceiptDetailsConstants.IMPORT)) {
					item.setImported(true);
				}
				line = reader.readLine();
				// System.out.println(item);
				items.add(item);
			}
			return items;
		} catch (IOException e) {
			logger.error("{} exception in processing receipt",
					ShoppingReceiptDetailsConstants.SHOPPING_RECEIPT_REQUEST);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("{} exception while closing buffered reader",
							ShoppingReceiptDetailsConstants.SHOPPING_RECEIPT_REQUEST);
				}
			}
		}
		return items;
	}

}