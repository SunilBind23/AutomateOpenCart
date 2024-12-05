package com.OpenCart.TestScripts;

import com.OpenCart.WebUtils.WebUtil;
import com.OpenCart.pages.BasePage;

public class ValidateSearchFunctionality extends BasePage {
 private WebUtil wt;
	
 public ValidateSearchFunctionality(WebUtil wt) {
	 this.wt=wt;
 }
	
	public void searchWithValidProduct() {
		
		
		wt.launchBrowser("chrome", 30);
		wt.WindowMaximise();
		wt.openURL("http://localhost/Opencart");
	}
}
