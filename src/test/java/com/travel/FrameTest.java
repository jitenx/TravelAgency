package com.travel;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.base.Base;
import com.base.Driver;

public class FrameTest extends Base {

	@Test
	public void testNestedFrames() {
		Driver.initDriver("chrome");
		driver().get("https://chercher.tech/practice/frames-example-selenium-webdriver");

		driver().switchTo().frame("frame1");
//		DriverManager.getDriver().findElement(By.id("topic")).sendKeys("Animals");
		driver().switchTo().frame("frame3");
		driver().findElement(By.id("a")).click();
		driver().switchTo().defaultContent();
		driver().switchTo().frame("frame2");
		Select select = new Select(driver().findElement(By.id("animals")));
		select.selectByIndex(1);

	}

	@AfterClass
	public void finish() {
		driver().quit();
	}
}
