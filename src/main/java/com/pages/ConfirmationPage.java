package com.pages;

import org.openqa.selenium.By;
import com.base.DriverManager;

public final class ConfirmationPage {
	
	
	private final By paymentId=By.xpath("//*[contains(text(),'Id')]/following-sibling::td");
	
	public String getPaymentId()
	{
		return DriverManager.getDriver().findElement(paymentId).getText();
	}

}
