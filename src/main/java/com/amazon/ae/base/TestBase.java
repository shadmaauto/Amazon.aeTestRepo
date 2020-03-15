package com.amazon.ae.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.amazon.ae.util.TestUtil;
import com.amazon.ae.util.WebEventListener;


public class TestBase {
    
	//global variables
	public static WebDriver driver;
	public static Properties prop;
	public static WebEventListener eventListener;
	public static EventFiringWebDriver edriver;
	
	public TestBase() {
		  try {	prop=new Properties();
		  	FileInputStream ip=new FileInputStream("C:/Users/shadm/Documents/f2/org.com.amazonAE/src/main/java/com/amazon/ae/config/config.properties");
			prop.load(ip);
		}catch (FileNotFoundException fe) {
			// TODO Auto-generated catch block
			fe.printStackTrace();
		} 
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void initialization() {
		String browsername=prop.getProperty("browser");
		if(browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}else if(browsername.equals("FF")) {
			System.setProperty("webdriver.firefox.driver","drivers/geckodriver.exe");
		}
		
		edriver=new EventFiringWebDriver(driver);
		eventListener=new WebEventListener();
		edriver.register(eventListener);
		driver=edriver;
		
		
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	    driver.get(prop.getProperty("url"));
	}
}
