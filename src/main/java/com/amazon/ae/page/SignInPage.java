package com.amazon.ae.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.ae.base.TestBase;

public class SignInPage extends TestBase {

	
	@FindBy(name="email")
	WebElement email;

	@FindBy(id="continue")
	WebElement continueButton;
	
	@FindBy(id="ap_password")
	WebElement password;
	
	@FindBy(id="signInSubmit")
	WebElement signInButton;
	
	@FindBy(xpath="//span[contains(text),'Need help']")
	WebElement needHelpLink;
	
	@FindBy(xpath="//span[@id='auth-create-account-link']")
	WebElement createActLink;
	
	@FindBy(xpath="//i[@class='a-icon a-icon-logo']")
	WebElement amazonLogoImg;
	
	public SignInPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateSignInpageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateAmazonLogo() {
		return amazonLogoImg.isDisplayed();
	}
	
	public HomePage signIn(String em,String pwd) {
		email.sendKeys(em);
		continueButton.click();
		password.sendKeys(pwd);
		signInButton.click();
		
		return new HomePage();
	}
}
