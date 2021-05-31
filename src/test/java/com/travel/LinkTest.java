package com.travel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.base.Base;
import com.base.Driver;
import com.base.DriverManager;

public class LinkTest extends Base {

	@Test
	public void testAllLinks() {
		Driver.initDriver("chrome");
		driver().get("http://ww1.yoniflenner.net/");
		driver().switchTo().frame(0);
		getAllLinks();
		WebElement fontCheck=DriverManager.getDriver().findElement(By.xpath("//*[@id=\"adBlock\"]/table/tbody/tr[1]/td/div/div/div/div/a"));
		logInfo("Font size is : "+fontCheck.getCssValue("font-size"));
		logInfo("Font type is : "+fontCheck.getCssValue("font-type"));
		logInfo("Font color is : "+fontCheck.getCssValue("color"));
		logInfo("With function "+fontFunc(fontCheck).getFontBGColor());
		logInfo("With function "+fontFunc(fontCheck).getFontColor());
		logInfo("With function "+fontFunc(fontCheck).getFontSize());
		logInfo("With function "+fontFunc(fontCheck).getFontType());
		driver().switchTo().defaultContent();
		WebElement fontCheck1=driver().findElement(By.xpath("//*[@id='privacy-policy-link']/a"));
		logInfo("Font1 size is : "+fontCheck1.getCssValue("font-size"));
		logInfo("Font1 type is : "+fontCheck1.getCssValue("font-type"));
		logInfo("Font1 color is : "+fontCheck1.getCssValue("color"));
		getAllLinks();

	}
	@AfterClass
	public void finish()
	{
		tearDown();
	}

}
