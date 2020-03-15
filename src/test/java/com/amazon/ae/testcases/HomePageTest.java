package com.amazon.ae.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.ae.base.TestBase;
import com.amazon.ae.page.BestSellersPage;
import com.amazon.ae.page.HomePage;
import com.amazon.ae.page.SignInPage;
import com.amazon.ae.util.TestUtil;

public class HomePageTest extends TestBase {

	HomePage hmPgObj;
	SignInPage signInPg;
	BestSellersPage bstSellerPgObj;
	String sheetName="SearchTestData";
	
	public HomePageTest() {
		super();
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
	 String hmPgTitle=	hmPgObj.getHomepageTitle();
	 Assert.assertEquals(hmPgTitle, "Souq is now Amazon.ae | Welcome to Amazon.ae Online Shopping for Electronics, Apparel, Computers, Grocery & more");
	}
	
	@Test(enabled=false)
	public void validateUserNameTextTest() {
		
		String uname=hmPgObj.getUserNameText();
		System.out.println(uname);
		Assert.assertEquals(uname, "Hello, shadma","User name text on homepage not matched");
	}
	
	@Test(enabled=true)
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
	public void tearDown() {
		driver.quit();
	}
	
	
}
