package com.base;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.extent.ExtentTestManager;

public class Base {
	
	public static WebDriver driver()
	{
		return DriverManager.getDriver();
	}

	public static void openUrl() {
		DriverManager.getDriver().get("https://blazedemo.com/index.php");
	}

	public static void clickElement(WebElement element) {
		element.click();
	}

	public static void type(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static String getTitle(WebElement element) {
		return element.getText();
	}

	public void tearDown() {
		DriverManager.getDriver().quit();
		DriverManager.unload();
	}

	public static String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public static void selectDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static void selectDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static void getAllLinks() {
		Map<String, String> map=new LinkedHashMap<>();
		
		List<WebElement> links = DriverManager.getDriver().findElements(By.tagName("a"));
		for (WebElement link : links) {
			
		//	logInfo(el.getText() + "\t" + el.getAttribute("href"));
			map.put(link.getText(), link.getAttribute("href"));

		}
		logInfoUnorderedList(map);
	}
	
	public static Font fontFunc(WebElement element)
	{
		String fontType=element.getCssValue("font-type");
		String fontColor=element.getCssValue("color");
		String fontSize=element.getCssValue("font-size");
		String bGColor=element.getCssValue("background-color");
		Font font=new Font();
		font.setFontBGColor(bGColor);
		font.setFontColor(fontColor);
		font.setFontSize(fontSize);
		font.setFontType(fontType);
		return font;
		
	}

	public static void logInfo(String logMessage) {
		ExtentTestManager.getTest().info(MarkupHelper.createLabel(logMessage, ExtentColor.WHITE));
	}

	public static void logPass(String logMessage) {
		ExtentTestManager.getTest().pass(MarkupHelper.createLabel(logMessage, ExtentColor.GREEN));
	}

	public static void logFail(String logMessage) {
		ExtentTestManager.getTest().fail(MarkupHelper.createLabel(logMessage, ExtentColor.RED));
	}

	public static void logSkip(String logMessage) {
		ExtentTestManager.getTest().skip(MarkupHelper.createLabel(logMessage, ExtentColor.GREY));
	}

	public static void logWarning(String logMessage) {
		ExtentTestManager.getTest().warning(MarkupHelper.createLabel(logMessage, ExtentColor.AMBER));
	}

	public static void logInfoUnorderedList(Object obj) {
		ExtentTestManager.getTest().info(MarkupHelper.createUnorderedList(obj).getMarkup());
	}

	public static void logInfoJsonCode(Object obj) {
		ExtentTestManager.getTest().info(MarkupHelper.createJsonCodeBlock(obj).getMarkup());
	}

	public String takeSnapShot() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

	}
}
