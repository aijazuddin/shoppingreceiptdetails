package com.msinc.shoppingreceipt.controller;

import java.io.File;
import java.util.logging.Logger;

import com.msinc.shoppingreceipt.constant.ShoppingReceiptDetailsConstants;

/**
 * @author Aijazuddin
 *
 */
public class ShoppingReceiptDetailsController {

	public static final Logger logger = Logger.getLogger(ShoppingReceiptDetailsController.class.getName()); 
	public void getShoppingReceiptDetails(File file) {
		logger.info(ShoppingReceiptDetailsConstants.SHOPPING_RECEIPT_REQUEST + " Received");
	}
}
