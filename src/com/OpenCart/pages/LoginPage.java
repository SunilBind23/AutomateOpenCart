package com.OpenCart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OpenCart.WebUtils.WebUtil;

public class LoginPage {
	private WebUtil wt;

	@FindBy(xpath = "//span[@class='caret']")
	private WebElement LogindDropDown;

	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginselect;
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement EmailAddress;
	
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement PassWordTextBox;

	public LoginPage(WebUtil wbt) {
		this.wt = wbt;
		PageFactory.initElements(wt.getDriver(), this);
	}

	public void clickloginDropDown() {
		wt.click(LogindDropDown, "LogindDropDown");
	}

	public void ClickOnLogin() {
		wt.click(loginselect, null);
	}
	
	public void EnterEmailAddress(String Email) {
		wt.type(EmailAddress, Email, "EmailAddressTextBox");
	}
	
	public void EnterPassWord(String PassWord) {
		wt.type(PassWordTextBox, PassWord, "PassWordTextBox");
	}

	

}
