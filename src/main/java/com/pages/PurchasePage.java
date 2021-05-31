package com.pages;

import org.openqa.selenium.By;

import com.base.DriverManager;

public final class PurchasePage {
	
	private final By totalCost=By.xpath("//*[contains(text(),'Total Cost')]/child::em");
	private final By purchaseFlightBtn=By.xpath("//input[@type='submit']");
	
	public String getTotalCost()
	{
		return DriverManager.getDriver().findElement(totalCost).getText();
	}
	
	public ConfirmationPage clickPurchaseFlightBtn()
	{
		DriverManager.getDriver().findElement(purchaseFlightBtn).click();
		return new ConfirmationPage();
	}
	public boolean priceFormat(String totalPrice)
	{
		return totalPrice.matches("[0-9]{3}[.]{1}[0-9]{2}");
			
	}
}
