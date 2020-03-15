package com.amazon.ae.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.ae.base.TestBase;
import com.amazon.ae.page.HomePage;
import com.amazon.ae.page.SignInPage;

public class SignInPageTest extends TestBase {
	SignInPage signInPgOb;
	HomePage hmPgObj;
	
	public SignInPageTest(){
		super();
		//calling parent class constructor so that properties get initialized
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		//signInPgOb=new SignInPage();
		hmPgObj=new HomePage();
		signInPgOb=hmPgObj.navigateToSignInPage();
	}
	
	@Test(priority=3)
	public void signInpgTitleTest() {
		//hmPgObj.validateNavigationToSignInPage();
		String title=signInPgOb.validateSignInpageTitle();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
	@Test(priority=2,enabled=false)
	public void signInpgLogoTest() {
		//hmPgObj.validateNavigationToSignInPage();
		boolean flag=signInPgOb.validateAmazonLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=1,enabled=true)
	public void signInTest() {
		hmPgObj=signInPgOb.signIn(prop.getProperty("email"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
