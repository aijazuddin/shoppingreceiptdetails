package com.msinc.shoppingreceipt;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.msinc.shoppingreceipt.model.Item;
import com.msinc.shoppingreceipt.model.Receipt;
import com.msinc.shoppingreceipt.service.impl.ShoppingReceiptDetailsServiceImpl;
import com.msinc.shoppingreceipt.util.ReceiptUtil;

/*import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;*/

/**
 * Unit test for simple App.
 */
public class ShoppingReceiptDetailsServiceImplTest 
{
	
	ShoppingReceiptDetailsServiceImpl shoppingReceiptDetails = new ShoppingReceiptDetailsServiceImpl();
	@Test
    public void testGetReceiptDetails(){
		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println("Output1: ");
		Receipt receipt = shoppingReceiptDetails.getShoppingReceiptDetails(new File(classLoader.getResource("input1.txt").getFile()));
		Assert.assertEquals(1.50, receipt.getSalesTax().doubleValue(),1e-15);
		Assert.assertEquals(29.83, receipt.getTotalAmount().doubleValue(),1e-15);
		System.out.println("\n\nOutput2: ");
		Receipt receipt2 = shoppingReceiptDetails.getShoppingReceiptDetails(new File(classLoader.getResource("input2.txt").getFile()));
		Assert.assertEquals(7.65, receipt2.getSalesTax().doubleValue(),1e-15);
		Assert.assertEquals(65.15, receipt2.getTotalAmount().doubleValue(),1e-15);
		System.out.println("\n\nOutput3: ");
		Receipt receipt3 = shoppingReceiptDetails.getShoppingReceiptDetails(new File(classLoader.getResource("input3.txt").getFile()));
		Assert.assertEquals(6.70, receipt3.getSalesTax().doubleValue(),1e-15);
		Assert.assertEquals(74.68, receipt3.getTotalAmount().doubleValue(),1e-15);
		
		//controller.getShoppingReceiptDetails(file);
    	//System.out.println("hello test class");
    }
	
	@Test
	public void testCalculateTaxes(){
		ClassLoader classLoader = getClass().getClassLoader();
		List<Item> items = ReceiptUtil.getItems(new File(classLoader.getResource("input1.txt").getFile()));
		shoppingReceiptDetails.calculateTaxes(items.get(0));
		Assert.assertEquals(12.49, items.get(0).getPriceWithTax().doubleValue(),1e-15);
		
		List<Item> items2 = ReceiptUtil.getItems(new File(classLoader.getResource("input2.txt").getFile()));
		shoppingReceiptDetails.calculateTaxes(items2.get(0));
		Assert.assertEquals(10.50, items2.get(0).getPriceWithTax().doubleValue(),1e-15);
		
		List<Item> items3 = ReceiptUtil.getItems(new File(classLoader.getResource("input3.txt").getFile()));
		shoppingReceiptDetails.calculateTaxes(items3.get(0));
		Assert.assertEquals(32.19, items3.get(0).getPriceWithTax().doubleValue(),1e-15);
		
	}
	
}
