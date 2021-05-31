package com.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.DriverManager;

public final class ReservePage {

	private final By price = By.xpath("//tbody/tr/td[6]");
//	private final By selectFlight = By.xpath("//tbody/tr/td[1]/input[1]");
	private final String selectFlightButton1 = "//td[contains(text(),'";
	private final String selectFlightButton2 = "')]//preceding-sibling::td//input";

	public Double getMinimumPrice() {
		List<WebElement> price1 =DriverManager.getDriver().findElements(price);
		ArrayList<Double> prices = new ArrayList<>();
		for (int i = 0; i < price1.size(); i++) {
			Double priceDouble = Double.valueOf(price1.get(i).getText().replace("$", ""));
			prices.add(priceDouble);
		}
		return Collections.min(prices);
	}

	public PurchasePage chooseThisFlight(Double minPrice) {
		String chooseThisFlightButton = selectFlightButton1 + minPrice + selectFlightButton2;
		DriverManager.getDriver().findElement(By.xpath(chooseThisFlightButton)).click();
		return new PurchasePage();
	}
}
