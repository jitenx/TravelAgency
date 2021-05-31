package com.travel;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.Base;
import com.base.Driver;
import com.pages.ConfirmationPage;
import com.pages.HomePage;
import com.pages.PurchasePage;
import com.pages.ReservePage;
import com.pages.VacationPage;

public final class PurchaseFlightsTest extends Base {

	private PurchaseFlightsTest() {
	}

	HomePage homepage;
	VacationPage vacationPage;
	PurchasePage purchasePage;
	ConfirmationPage confirmationPage;
	ReservePage reservePage;

	@Parameters({ "browser" })
	@BeforeClass()
	public void init(String browser) {
		Driver.initDriver(browser);
		openUrl();
//		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test()
	public void verifyTitle() {
		homepage = new HomePage();
		String title = homepage.getPageTitle();
		Assert.assertEquals(title, "Welcome to the Simple Travel Agency!");
		logInfo("Title of the wbepage is \"" + title + "\"");
	}

	@Test(dependsOnMethods = "verifyTitle")
	public void verifyVacationPage() {
		homepage.clickDestinationLink();
		vacationPage = new VacationPage();
		String destTitle = vacationPage.getDestinationTitle();
		logInfo("'Destination of the week' page title is \"" + destTitle + "\"");
		Assert.assertEquals(destTitle, "Destination of the week: Hawaii !");
		Assert.assertEquals(vacationPage.currentUrlContains("vacation"), true);
	}

	@Parameters({ "departureCity", "destinationCity" })
	@Test(dependsOnMethods = "verifyVacationPage")
	public void findFlights(String departureCity, String destinationCity) {
		vacationPage.clickWorldLink();
		homepage.setDepartureCity(departureCity);
		homepage.setDestinationCity(destinationCity);
		homepage.clickFindFlights();
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Departure City", departureCity);
		map.put("Destination City", destinationCity);
		logInfoUnorderedList(map);
	}

	@Test(dependsOnMethods = "findFlights")
	public void chooseFlight() {

		reservePage = new ReservePage();
		reservePage.chooseThisFlight(reservePage.getMinimumPrice());
	}

	@Test(dependsOnMethods = "chooseFlight")
	public void purchaseFlight() {
		purchasePage = new PurchasePage();
		String totalCost = purchasePage.getTotalCost();
		System.out.println("Total cost of the flight is: " + totalCost);
		logInfo("Total cost of the flight is: $" + totalCost);
		Assert.assertEquals(purchasePage.priceFormat(totalCost), true);
		purchasePage.clickPurchaseFlightBtn();
		logInfo("Clicked on Purchase Flight Button");

	}

	@Test(dependsOnMethods = "purchaseFlight")
	public void confirmPurchase() {
		confirmationPage = new ConfirmationPage();
		String paymentId = confirmationPage.getPaymentId();
		System.out.println("Payment ID: " + paymentId);
		logInfo("Payment ID: " + paymentId);

	}

	@AfterClass()
	public void closeBrowser() {
		tearDown();
	}

	// Code for for loop of choose flight

	/*
	 * List<WebElement> price = driver.findElements(By.xpath("//tbody/tr/td[6]"));
	 * System.out.println("Total columns are: " + price.size()); ArrayList<Double>
	 * prices = new ArrayList<Double>(); for (int i = 0; i < price.size(); i++) {
	 * Double priceDouble = Double.valueOf(price.get(i).getText().replace("$", ""));
	 * prices.add(priceDouble); }
	 * 
	 * Double minPrice = Collections.min(prices); System.out.println(minPrice);
	 * 
	 * String chooseThisFlightButton="//td[contains(text(),'"+minPrice+
	 * "')]//preceding-sibling::td//input";
	 * driver.findElement(By.xpath(chooseThisFlightButton)).click();
	 */

	/*
	 * List<WebElement> selectFlight =
	 * driver.findElements(By.xpath("//tbody/tr/td[1]/input[1]")); for (int i = 0; i
	 * < price.size(); i++) { Double priceMin =
	 * Double.valueOf(price.get(i).getText().replace("$", "")); if (priceMin * 100
	 * == minPrice * 100) { selectFlight.get(i).click();
	 * logInfo("Flight with lowest price is selected. Minimum flight price is $"
	 * +minPrice); break; }
	 * 
	 * }
	 */

}
