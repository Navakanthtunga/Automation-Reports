package com.extent.Scripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.extent.baselib.BaseClass;
import com.extent.reports.ReportsLibrary;

public class TestClass1 extends BaseClass {
	@Test(enabled = true, description = "I am test 1")
	public void method1() throws InterruptedException {
		driver.get("https://www.google.com");
		ReportsLibrary.addInfoStep("I am Info step of the method1");
		ReportsLibrary.addPassStep("I am Pass Step of the method1");
	}
	@Test(enabled = true, description = "I am test 2")
	public void method2() throws InterruptedException {
		ReportsLibrary.addInfoStep("I am Info step of the method2");
		ReportsLibrary.addPassStep("I am Pass Step of the method2");
	}
	@Test(enabled = true, description = "I am test 3")
	public void method3() throws InterruptedException {
		ReportsLibrary.addInfoStep("I am Info step of the method3");
		ReportsLibrary.addPassStep("I am Pass Step of the method3");
		ReportsLibrary.addWarningStep("I am Warning Step of the method3");
	}
	@Test(enabled = false, description = "I am test 4")
	public void method4() {
		ReportsLibrary.addInfoStep("I am Info step of the method4");
		ReportsLibrary.addPassStep("I am Pass Step of the method4");
		ReportsLibrary.addWarningStep("I am Warning Step of the method4");
	}
	@Test(enabled = true, description = "I am test 5")
	public void method5() {
		ReportsLibrary.addInfoStep("I am Info step of the method5");
		ReportsLibrary.addPassStep("I am Pass Step of the method5");
		ReportsLibrary.addWarningStep("I am Warning Step of the method5");
		driver.findElement(By.id(("navakanth"))).click();
	}
	@Test(enabled = false, description = "I am test 7")
	public void method7() {
		ReportsLibrary.addInfoStep("I am Info step of the method7");
		ReportsLibrary.addPassStep("I am Pass Step of the method7");
		ReportsLibrary.addWarningStep("I am Warning Step of the method7");
		
		Assert.fail();
	}
	
}
