package com.OpenCart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.OpenCart.WebUtils.WebUtil;

public class RegisterPage {
	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement FirstName;

	public WebElement GetFirstName() {
		return FirstName;
	}

	public void setFirstName(WebElement firstName) {
		FirstName = firstName;
	}

	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement LastName;

	public WebElement GetLastName() {
		return LastName;
	}

	public void setLastName(WebElement lastname) {
		LastName = lastname;
	}

	@FindBy(xpath = "//input[@name='email']")
	private WebElement EMail;

	public WebElement getEmail() {
		return EMail;
	}

	public void setEmail(WebElement email) {
		EMail = email;
	}

	@FindBy(xpath = "//input[@name='telephone']")
	private WebElement Telephone;

	public WebElement getTeElement() {
		return Telephone;
	}

	public void setTelephone(WebElement telephone) {
		Telephone = telephone;
	}

	@FindBy(xpath = " //input[@name='password']")
	private WebElement Password;

	public WebElement getPassword() {
		return Password;
	}

	@FindBy(xpath = " //input[@name='confirm']")
	private WebElement PasswordConfirm;

	public WebElement getConfirsPassWord() {
		return PasswordConfirm;
	}

	public void setConcirmPassword(WebElement confirmPassword) {
		PasswordConfirm = confirmPassword;
	}

	private WebUtil wbt;

	public RegisterPage(WebUtil wbt) {
		this.wbt = wbt;
	}

	// First Name Last Name E-Mail Telephone Password Password Confirm
}
