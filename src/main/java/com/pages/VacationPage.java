package com.pages;

import org.openqa.selenium.By;

import com.base.DriverManager;

public final class VacationPage {
	private final By destinationTitle=By.xpath("//div[contains(text(),'Destination of the week')]");
	private final By travelTheWorldLink=By.xpath("//a[contains(text(),'Travel The World')]");

	public String getDestinationTitle()
	{
		return DriverManager.getDriver().findElement(destinationTitle).getText();
	}
	public HomePage clickWorldLink()
	{
		DriverManager.getDriver().findElement(travelTheWorldLink).click();
		return new HomePage();
	}
	public boolean currentUrlContains(String text)
	{
		String current=DriverManager.getDriver().getCurrentUrl();
		if(current.contains(text))
			return true;
		else
			return false;
	}
}
