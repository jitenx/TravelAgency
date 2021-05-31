package com.listener;

/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.aventstack.extentreports.MediaEntityBuilder;
import com.base.Base;
import com.extent.ExtentManager;
import com.extent.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTestNG extends Base implements ITestListener {
	@Override
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started *** at "+context.getStartDate());
	}
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " ending *** at "+context.getEndDate());
		logInfo(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		logPass("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		logInfo("Test Execuction Thread ID: "+Thread.currentThread().getId());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logFail("*** Test execution " + result.getMethod().getMethodName() + " failed...");

		ExtentTestManager.getTest()
				.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(takeSnapShot(), "Screenshot").build());

	}
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		logSkip("*** Test " + result.getMethod().getMethodName() + " skipped...");
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
		logInfo("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
}
