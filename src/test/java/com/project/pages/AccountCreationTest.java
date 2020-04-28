package com.project.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.common.BaseTest;

public class AccountCreationTest extends BaseTest {

	@Test
	public void accountCreationFlow() {
		try {
			cmPage.openHomePage(prop.getProperty("URL"));
			System.out.println("-----------------------Cliking on Sign in link-----------------------");
			cmPage.clickElementByXpath(prop.getProperty("signInXpath"));
			cmPage.accountCreationFlow(prop);
			cmPage.explicitWait((prop.getProperty("createAccountHeaderXpath")));
			Assert.assertEquals(cmPage.gretTextbyXpath(prop.getProperty("createAccountHeaderXpath")),
					prop.getProperty("createAccountExpectedHeader"));
			cmPage.registrationFlow(prop);
			cmPage.explicitWait((prop.getProperty("orderHistoryTab")));
			Assert.assertTrue(driver.findElement(By.xpath(prop.getProperty("orderHistoryTab"))).isDisplayed(), "Order history button is not displyed");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
