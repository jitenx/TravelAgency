package com.travel;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.Base;
import com.base.Driver;
import com.pages.HomePage;
import com.pages.ReservePage;


public final class FlightsTest extends Base {
	@Parameters({ "browser", "departureCity", "destinationCity" })
	@Test
	public void purchageFlight(String browser, String departureCity, String destinationCity) {
		Driver.initDriver(browser);
		openUrl();
		String id = new HomePage().clickDestinationLink().clickWorldLink().setDepartureCity(departureCity)
				.setDestinationCity(destinationCity).clickFindFlights()
				.chooseThisFlight(new ReservePage().getMinimumPrice()).clickPurchaseFlightBtn().getPaymentId();
		logInfo("Payment ID: " + id);
		tearDown();
	}
}
