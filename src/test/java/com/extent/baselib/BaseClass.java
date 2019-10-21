package com.extent.baselib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	static public String sDirPath = System.getProperty("user.dir");
	public static String sConfigFile;
	public static String sKirwaFile;
	

	@BeforeClass
	public void launch() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}
}
