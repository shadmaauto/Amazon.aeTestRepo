package com.amazon.ae.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.ae.base.TestBase;
import com.amazon.ae.page.BestSellersPage;
import com.amazon.ae.page.HomePage;
import com.amazon.ae.page.SignInPage;
import com.amazon.ae.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends TestBase {

	HomePage hmPgObj;
	SignInPage signInPg;
	BestSellersPage bstSellerPgObj;
	String sheetName="SearchTestData";
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	public HomePageTest() {
		super();
	}
	
	
	//code to define extent report parameters --main aim to generate screenshot and save in jenkins
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Shadma Windows");
		extent.addSystemInfo("User Name", "si");
		extent.addSystemInfo("Environment", "QA");
		
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		bstSellerPgObj =new BestSellersPage();
		hmPgObj=new HomePage();
		signInPg=hmPgObj.navigateToSignInPage();
		hmPgObj=signInPg.signIn(prop.getProperty("email"), prop.getProperty("password"));
	}
	
	@Test(enabled=true)
	public void homePageTitleTest() {
		extentTest=extent.startTest("homePageTitleTest");
	 String hmPgTitle=	hmPgObj.getHomepageTitle();
	 Assert.assertEquals(hmPgTitle, "Souq is now Amazon.aeee | Welcome to Amazon.ae Online Shopping for Electronics, Apparel, Computers, Grocery & more");
	}
	
	@Test(enabled=false)
	public void validateUserNameTextTest() {
		
		String uname=hmPgObj.getUserNameText();
		System.out.println(uname);
		Assert.assertEquals(uname, "Hello, shadma","User name text on homepage not matched");
	}
	
	@Test(enabled=false)
	public void verifyBestSellersLinkTest() {
		
			bstSellerPgObj=hmPgObj.clickBestSellersLink();
	}
	
	@DataProvider
	public Object[][] getSearchTestData() {
		Object[][] testData=TestUtil.getTestData(sheetName);
		return testData;
	}
	
	@Test(enabled=false,dataProvider="getSearchTestData")
	public void verifySearchByText(String SearchText) {
		hmPgObj.searchByText(SearchText);
	}
	
		
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = HomePageTest.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
	
	
	
}
