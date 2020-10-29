package com.msinc.shoppingreceipt.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShoppingReceiptDetailsConstants {
	public static final String SHOPPING_RECEIPT_REQUEST = "SHOPPING_RECEIPT_REQUEST";
	public static final String IMPORT = "IMPORT";
	public static final double IMPORT_DUTY_TAX = 0.05;
	public static final double SALES_TAX = 0.1;
	public static final Set<String> books = new HashSet<>(Arrays.asList("BOOK"));
	public static final Set<String> food = new HashSet<>(Arrays.asList("CHOCOLATE"));
	public static final Set<String> medicine = new HashSet<>(Arrays.asList("PILLS"));
}
