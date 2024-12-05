package com.OpenCart.TestScripts;

import java.util.Map;
import com.OpenCart.WebUtils.WebUtil;
import com.OpenCart.pages.LoginPage;

public class ValidateLoginFunctionality {
	private WebUtil wbt;

	public ValidateLoginFunctionality(WebUtil wbt) {
		this.wbt = wbt;
	}

	public void LoginFunction(String TestCaseId) {
		wbt.launchBrowser("chrome", 20);
		wbt.openURL("http://localhost/Opencart");
		wbt.WindowMaximise();

		ExcelReader exRead = new ExcelReader();
		Map<String, String> data = exRead.getExcelData("LoginData", TestCaseId);
		String Email = data.get("EmailAddress").trim();
		String Password = data.get("Password").trim();

		LoginPage lp = new LoginPage(wbt);
		lp.clickloginDropDown();
		lp.ClickOnLogin();
		lp.EnterEmailAddress(Email);
		lp.EnterPassWord(Password);
	}

	public void TC_LF_001LoginWithVailidCradintial() {
		LoginFunction("TC_LF_001");
		wbt.closeAllBrowser();
	}

	public void TC_LF_002_InvalidEmailAddressInvalidPassword() {
		LoginFunction("TC_LF_002");
		
		wbt.closeAllBrowser();
	}
	public void TC_LF_003_Login_With_Invalid_EmailAddressandvalidPassword() {
		LoginFunction("TC_LF_003");
		wbt.closeAllBrowser();
	}
	public void TC_LF_004_validEmailAddressandinvalidPassword() {
		LoginFunction("TC_LF_004");
		wbt.closeAllBrowser();
	}
	public void TC_LF_005_LoginwithoutProvidinganyCredentials() {
		LoginFunction("TC_LF_005");
		wbt.closeAllBrowser();
	}
	
}
	/*TC_LF_003_Login_With_Invalid_EmailAddressandvalidPassword
	 * TC_LF_004_validEmailAddressandinvalidPassword
	 * TC_LF_005_LoginwithoutProvidinganyCredentials
	 * 
	 * TC_LF_006_ValidateForgottenPasswordlinkAvailableIntheLoginpage
	 * TC_LF_007  
	 * TC_LF_008
	 * TC_LF_009
	 * TC_LF_010
	 * TC_LF_011
	 * TC_LF_012
	 * TC_LF_013
	 */
//	 */
	
	

