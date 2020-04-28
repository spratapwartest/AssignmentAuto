package com.project.common;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage {
	WebDriver driver;

	public CommonPage(WebDriver driver) {
		this.driver = driver;
	}

	public void openHomePage(String HomepageURL) {
		driver.get(HomepageURL);
	}

	public String getRandomEmailAddress() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString + "@mailinator.com";
	}

	public void explicitWait(String elementXpath) {
		WebDriverWait wait = new WebDriverWait(driver, 15000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
	}

	public void accountCreationFlow(Properties prop) {
		String emailAddress = getRandomEmailAddress();
		System.out.println("Generated email address is " + emailAddress);
		explicitWait(prop.getProperty("emailAddressFieldXpath"));
		enterValueInInputBox(prop.getProperty("emailAddressFieldXpath"), emailAddress);
		clickElementByXpath(prop.getProperty("createAccountBtnXpath"));
	}

	public void registrationFlow(Properties prop) {

		clickElementByID(prop.getProperty("genderID"));
		enterValueInInputBox(prop.getProperty("CustFNXpath"), prop.getProperty("testingData"));
		enterValueInInputBox(prop.getProperty("CustLNXpath"), prop.getProperty("testingData"));
		enterValueInInputBox(prop.getProperty("passwordXpath"), prop.getProperty("passwordValue"));
		enterValueInInputBox(prop.getProperty("fnNameXpath"), prop.getProperty("testingData"));
		enterValueInInputBox(prop.getProperty("lnName"), prop.getProperty("testingData"));
		enterValueInInputBox(prop.getProperty("addressXpath"), prop.getProperty("testingData"));
		enterValueInInputBox(prop.getProperty("cityXpath"), prop.getProperty("testingData"));
		selectValueByVisibleText(prop.getProperty("stateXpath"), prop.getProperty("State"));
		enterValueInInputBox(prop.getProperty("postalXpath"), prop.getProperty("postalCode"));
		enterValueInInputBox(prop.getProperty("phoneXpath"), prop.getProperty("phoneNumber"));
		enterValueInInputBox(prop.getProperty("addressaliasXpath"), prop.getProperty("testingData"));
		clickElementByXpath(prop.getProperty("registerButtonXpath"));
	}

	public void clickElementByXpath(String elementXpath) {
		try {
			driver.findElement(By.xpath(elementXpath)).click();
		} catch (ElementNotVisibleException et) {
			et.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public String gretTextbyXpath(String elementXpath) {
		String actualText = null;
		try {
			actualText = driver.findElement(By.xpath(elementXpath)).getText();
		} catch (ElementNotVisibleException et) {
			et.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return actualText;
	}

	public void clickElementByID(String elementID) {
		try {
			driver.findElement(By.id(elementID)).click();
		} catch (ElementNotVisibleException et) {
			et.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void enterValueInInputBox(String elementXpath, String value) {
		try {
			driver.findElement(By.xpath(elementXpath)).clear();
			driver.findElement(By.xpath(elementXpath)).sendKeys(value);
		} catch (ElementNotVisibleException et) {
			et.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	public void selectValueByVisibleText(String elementXpath, String value) {
		try {
			Select sel = new Select(driver.findElement(By.xpath(elementXpath)));
			sel.selectByVisibleText(value);
		} catch (ElementNotVisibleException et) {
			et.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
