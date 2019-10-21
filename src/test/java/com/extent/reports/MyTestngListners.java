package com.extent.reports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.extent.baselib.BaseClass;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

public class MyTestngListners implements ITestListener {
	static Date date = new Date();
	static SimpleDateFormat sdtf = new SimpleDateFormat("dd-MM-yy_hh_mm_ss");
	static String sTime = sdtf.format(date);
	public static String extentReportDest = BaseClass.sDirPath + "\\reports\\HTML_REPORTS\\AutomationReport_" + sTime + ".html";
	private ExtentReports reporter = new ExtentReports(extentReportDest, true, DisplayOrder.NEWEST_FIRST,
			NetworkMode.OFFLINE, Locale.ENGLISH);
	public static ExtentTest testReporter;
	public void onTestStart(ITestResult result) {
		testReporter = reporter.startTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
	}

	public void onTestSuccess(ITestResult result) {
		ReportsLibrary.addPassStep("Test is pass");
		reporter.endTest(testReporter);
		reporter.flush();
	}

	public void onTestFailure(ITestResult result) {
		ReportsLibrary.addFailStep("Test is fail");

		try {
			capture(BaseClass.driver, result.getName());
			String screenShotPath = capture(BaseClass.driver, result.getName().toString());
			ReportsLibrary.addFailStep("Below is the screen where script failed with " + "\" "
					+ ((result.getThrowable().toString().split(":"))[0])
							.split("\\.")[((result.getThrowable().toString().split(":"))[0]).split("\\.").length - 1]
									.toString()
					+ " \"" +"."+testReporter.addScreenCapture(screenShotPath));
		} catch (Exception e) {
			ReportsLibrary.addFailStep("Not able to get Screenshots");
		}
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		reporter.loadConfig(new File(BaseClass.sDirPath + "\\extent-config.xml"));
		reporter.addSystemInfo("Created By: ", "Test Automation");
	}

	public void onFinish(ITestContext context) {
		reporter.endTest(testReporter);
		reporter.flush();
	}

	public static String capture(WebDriver driver, String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = BaseClass.sDirPath + "\\screenshots\\" + screenShotName + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}
}
