package com.amazon.ae.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.ae.base.TestBase;
import com.amazon.ae.page.BestSellersPage;
import com.amazon.ae.page.HomePage;
import com.amazon.ae.page.SignInPage;

public class BestSellersPageTest extends TestBase {
 
	HomePage hmPgObj;
	SignInPage signInPgObj;
	BestSellersPage bestSllrPgObj;
	
	public BestSellersPageTest() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@BeforeMethod
	public void seturp() {
		initialization();
		bestSllrPgObj=new BestSellersPage();
		hmPgObj=new HomePage();
		signInPgObj=hmPgObj.navigateToSignInPage();
		hmPgObj=signInPgObj.signIn(prop.getProperty("email"), prop.getProperty("password"));
		bestSllrPgObj=hmPgObj.clickBestSellersLink();
		
	}
	
	@Test
	public void verifyBestSellersPageBannerText() {
		Boolean flag=bestSllrPgObj.checkBestSellersPageBannerText();
		Assert.assertTrue(flag);
	}
}
