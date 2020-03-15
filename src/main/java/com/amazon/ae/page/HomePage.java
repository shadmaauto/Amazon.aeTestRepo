package com.amazon.ae.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.ae.base.TestBase;

public class HomePage extends TestBase {

	//pagefactory approach of maintaining object repository of web elements
	@FindBy(xpath="//a[@id='nav-link-accountList']")
	@CacheLookup
	WebElement signInLink;
	
	@FindBy(xpath="//a[contains(text(),'Best sellers')]")
	WebElement bestSellerLink;
	
	@FindBy(xpath="//span[contains(text(),'Hello, shadma')]")
	WebElement userName;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchBox;
	
	@FindBy(xpath="//input[@type='submit' and @class='nav-input']")
	WebElement searchIcon;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public SignInPage navigateToSignInPage() {
		signInLink.click();
		return new SignInPage();
	}
	
	public String getHomepageTitle() {
		return driver.getTitle();
	}
	
	public String getUserNameText() {
		return userName.getText();
	}
	
	public BestSellersPage clickBestSellersLink() {
		bestSellerLink.click();;
		return new BestSellersPage();
		
	}
	
	public void searchByText(String srchTxt) {
		searchBox.sendKeys(srchTxt);
		searchIcon.click();
	}
	
	
}
