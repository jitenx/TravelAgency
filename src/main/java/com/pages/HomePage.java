package com.pages;

import org.openqa.selenium.By;

import com.base.DriverManager;

public final class HomePage {

	private final By title = By.xpath("//h1[contains(text(),'Welcome to the Simple Travel Agency!')]");
	private final By destinationLink = By.xpath("//a[contains(text(),'destination of the week! The Beach!')]");
	private final By departureCity = By.name("fromPort");
	private final By destinationCity = By.name("toPort");
	private final By findFlightsButton = By.xpath("//input[@type='submit']");

	public String getPageTitle() {
		return DriverManager.getDriver().findElement(title).getText();
	}

	public VacationPage clickDestinationLink() {
		DriverManager.getDriver().findElement(destinationLink).click();
		return new VacationPage();

	}

	public HomePage setDepartureCity(String departCity) {
		DriverManager.getDriver().findElement(departureCity).sendKeys(departCity);
		return this;
	}

	public HomePage setDestinationCity(String destCity) {
		DriverManager.getDriver().findElement(destinationCity).sendKeys(destCity);
		return this;
	}

	public ReservePage clickFindFlights() {
		DriverManager.getDriver().findElement(findFlightsButton).click();
		return new ReservePage();
	}
}
