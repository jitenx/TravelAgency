package com.base;

import java.util.Objects;
import java.util.logging.Level;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class Driver {
	private Driver() {

	}

	public static void initDriver(String browser) {
		if (Objects.isNull(DriverManager.getDriver())) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.silentOutput", "true");
				java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--silent", "headless", "disable-extensions", "disable-infobars",
						"start-maximized");
				DriverManager.setDriver(new ChromeDriver(options));
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.addArguments("headless", "disable-extensions", "disable-infobars", "start-maximized");
				DriverManager.setDriver(new EdgeDriver(options));
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				DriverManager.setDriver(new FirefoxDriver(options));
			} else if (browser.equalsIgnoreCase("brave")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.setBinary("/usr/bin/brave-browser");
				options.addArguments("disable-extensions", "disable-infobars", "start-maximized");
				DriverManager.setDriver(new ChromeDriver(options));
			} else {
				System.out.println("Browser not available");
			}
		}
	}

	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}
}
