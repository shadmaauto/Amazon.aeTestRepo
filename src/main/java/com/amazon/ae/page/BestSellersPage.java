package com.amazon.ae.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.ae.base.TestBase;

public class BestSellersPage extends TestBase {

	@FindBy(xpath="//div[contains(text(), 'Amazon Best Sellers')]")
	WebElement bestSellerPgLabel;
	
	public BestSellersPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkBestSellersPageBannerText() {
	  return bestSellerPgLabel.isDisplayed();
	}

}
