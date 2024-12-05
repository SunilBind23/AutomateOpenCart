package com.OpenCart.runner;

import java.io.IOException;

import com.OpenCart.TestScripts.ValidateLoginFunctionality;
import com.OpenCart.TestScripts.ValidateSearchFunctionality;
import com.OpenCart.WebUtils.WebUtil;
import com.OpenCart.pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class RunScripts {
	public static void main(String[] args) throws IOException {
		ExtentReports extReport = new ExtentReports();
		ExtentSparkReporter extSpark = new ExtentSparkReporter("D:\\OpenCartProject\\ValidateLoginFunctionality.html");
		extReport.attachReporter(extSpark);

		WebUtil wbt = new WebUtil();
		ValidateLoginFunctionality login = new ValidateLoginFunctionality(wbt);

		// Test Case 1
		ExtentTest extTest = null;
		
		extTest = extReport.createTest("TC_LF_001LoginWithValidCredentials");
		wbt.setExtentest(extTest);
		login.TC_LF_001LoginWithVailidCradintial();
		System.out.println("test case 1 done");
		// Test Case 2
		
		
		extTest = extReport.createTest("TC_LF_002InvalidEmailAddressInvalidPassword");
		wbt.setExtentest(extTest);
		login.TC_LF_002_InvalidEmailAddressInvalidPassword();
		System.out.println("test case 2 done");
		
		
		extTest = extReport.createTest("TC_LF_003_Login_With_Invalid_EmailAddressandvalidPassword");
		wbt.setExtentest(extTest);
		login.TC_LF_003_Login_With_Invalid_EmailAddressandvalidPassword();
		System.out.println("test case 3 done");
		
		
		extTest = extReport.createTest("TC_LF_004_validEmailAddressandinvalidPassword");
		wbt.setExtentest(extTest);
		login.TC_LF_004_validEmailAddressandinvalidPassword();
		System.out.println("test case 4 done");
		
		
		extTest = extReport.createTest("TC_LF_005_LoginwithoutProvidinganyCredentials");
		wbt.setExtentest(extTest);
		login.TC_LF_005_LoginwithoutProvidinganyCredentials();
		System.out.println("test case 5 done");
		
		extReport.flush();

	}
}
