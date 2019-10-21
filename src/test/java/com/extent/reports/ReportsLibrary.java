package com.extent.reports;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class ReportsLibrary {
	public static void elementDisplay(WebElement element, String elementName) 
	{
		try 
	    {
	        if(element.isDisplayed()){
	      MyTestngListners.testReporter.log(LogStatus.PASS,elementName+" is displayed");
	        }
	    } 
	    catch (Exception e) 
	    {
	    	MyTestngListners.testReporter.log(LogStatus.FAIL,elementName+" is not displayed");
	    }
	}
	public static void addPassStep(String StepDescription){
		MyTestngListners.testReporter.log(LogStatus.PASS,StepDescription);
	}
	public static void addFailStep(String StepDescription){
		MyTestngListners.testReporter.log(LogStatus.FAIL,StepDescription);
	}
	public static void addWarningStep(String StepDescription){
		MyTestngListners.testReporter.log(LogStatus.WARNING,StepDescription);
	}
	public static void addInfoStep(String StepDescription){
		MyTestngListners.testReporter.log(LogStatus.INFO,StepDescription);
	}
}
