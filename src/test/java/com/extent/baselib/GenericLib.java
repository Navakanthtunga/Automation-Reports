/***********************************************************************
 * @author 			:		Navakanth Tunga
 * @description		: 		Generic library with reusable methods that can be used across projects.
 * @method			:		getCongigValue()
 * @method			:		toReadExcelData()
 * @method			:		toWriteIntoExcel()
 * @method 			:		setStatus()
 * @method 
 */

package com.extent.baselib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;


public class GenericLib {
	public static String sFile;
	public static int iPassCount = 0;
	public static int iFailCount = 0;
	public static int iSkippedCount = 0;
	static public String sDirPath = System.getProperty("user.dir");
	static public String rDirPath = sDirPath+"\\src\\test\\resources";
	public static String sTestDataFile = sDirPath+"\\TestData.xlsx";
	public static String sTestDataFile1 = sDirPath+"\\Data.xlsx";
	public static String sConfigFile = sDirPath+"\\Config.properties";
	public static String imagepath=sDirPath+"\\src\\test\\resources\\images\\Liqvid.png";
	static public String user_Name= System.getProperty("user.name");
	
	/*
	 * @author: Navakanth Tunga
	 * Description: To read the basic environment settings
	 * data from config file
	 */
	public static String getCongigValue(String sFile, String sKey) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);
			sValue = prop.getProperty(sKey);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sValue;
	}
	/*
	 * @author: Navakanth Tunga
	 * Description: To read the basic environment settings
	 * data from config file
	 */
	public static void setCongigValue(String sFile, String sKey, String sValue) {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(sFile));
			prop.load(fis);
			fis.close();

			FileOutputStream fos = new FileOutputStream(new File(sFile));
			prop.setProperty(sKey, sValue);
			prop.store(fos, "Updating folder path");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * @author: Navakanth Tunga
	 * Description: To read test data from excel sheet
	 */
	public static String[] toReadExcelData(String sTestCaseID)
	{
		String sData[]=null;
		try
		{
			FileInputStream fis = new FileInputStream(sTestDataFile);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet("Liqvid");
			int iRowNum= sht.getLastRowNum();
			int k=0;
			for(int i=1;i<=iRowNum;i++)
			{
				if(sht.getRow(i).getCell(0).toString().equals(sTestCaseID))
				{
					int iCellNum=sht.getRow(i).getLastCellNum();
					sData= new String[iCellNum];
					for(int j=0;j<iCellNum;j++)		
					{
						sData[j]=sht.getRow(i).getCell(j).getStringCellValue();									
					}
					break;
				}
			}	
		}
		
		catch(Exception e)
		{	
			e.printStackTrace();
		}
		return sData;
	}
	/*
	 * @author: Navakanth Tunga
	 * Description: To write test data into excel sheet
	 */
	public static void toWriteExcelData(String sTestCaseID,String userName,String password)
	{
		try
		{
			FileInputStream fis = new FileInputStream(sTestDataFile);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet("Liqvid");
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
			if (sht.getRow(i).getCell(0).toString().equalsIgnoreCase(sTestCaseID)) {
				sht.getRow(i).getCell(1).setCellValue(userName);
				sht.getRow(i).getCell(2).setCellValue(password);
				}
			}
			FileOutputStream fos = new FileOutputStream(sTestDataFile);
			wb.write(fos);
		}
		catch(Exception e)
		{	
			e.printStackTrace();
		}
	}
	
	public  static void setStatus(String sName, String sResult,ArrayList sTestName, ArrayList sStatus)
	{
		sName=sName.replace("test","");
		sTestName.add(sName);
		sStatus.add(sResult);
		
		if(sResult.equals("Passed"))
		{iPassCount=iPassCount+1;
		}else if(sResult.equals("Failed"))
		{iFailCount = iFailCount+1;
		}else
		{iSkippedCount = iSkippedCount+1;
		}
	}
	public static int getIntegerNumber(WebElement element){

		String string=element.getText();
		 int integerNumber= Integer.parseInt(string);
		 return integerNumber;
	}
	public static String getSubstring(String string,int beginIndex){
		String newString=string.substring(beginIndex);
		return newString;
	}
}