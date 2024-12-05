package com.OpenCart.WebUtils;



import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class WebUtil {

	private WebDriver driver;

	private static ExtentTest et;

	public static void setExtentest(ExtentTest extenTestVariable) { 

		et = extenTestVariable;
	}


	
	public void WindowMaximise() {
		driver.manage().window().maximize();
	}
	public WebDriver launchBrowser(String brwoserName, int enterWaitTimeInSecond) {
		try {
			switch (brwoserName.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				et.log(Status.INFO, brwoserName + " browser launched successfully ");
				break;
			case "firefox":
				driver = new FirefoxDriver();
				et.log(Status.INFO, brwoserName + "browser launched successfully ");
				break;
			case "edge":
				driver = new EdgeDriver();
				et.log(Status.INFO, brwoserName + " browser launched successfully ");
				break;
			case "safari":
				driver = new SafariDriver();
				et.log(Status.INFO, brwoserName + " browser launched successfully ");
				break;
			default:
				et.log(Status.FAIL,
						brwoserName + " -  This is not a valid browser name please enter valid browser name ");
			}
		} catch (SessionNotCreatedException e) {
			et.log(Status.FAIL, "this is a exception which name is -  " + e.getMessage());

		} catch (NullPointerException e) {
			et.log(Status.FAIL, "this is a null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "this is a exception which name is -  " + e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return driver;
	}

	
	public WebDriver getDriver() {
		return driver;
	}

	public void implicitWait(int enterWaitTimeInSecond) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(enterWaitTimeInSecond));
		} catch (Exception e) {
			et.log(Status.FAIL, "implicity wait is not working properly and here Exception is - " + e.getMessage());
		}
	}


	
	public void closeBrowser() throws SocketException {
		try {
			driver.close();
			et.log(Status.INFO, "Browser is closed successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "Browser not closed and exception is - " + e.getMessage());
		}

	}


	public void closeAllBrowser() {
		try {
			driver.quit();
			et.log(Status.INFO, "Browser is Quited successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "Browser not Quited and exception is - " + e.getMessage());
		}
	}


	public void openURL(String url) {
		try {
			driver.get(url);
			et.log(Status.INFO, " URL hitted successfully here URL is - " + url);
		} catch (InvalidArgumentException e) {
			et.log(Status.FAIL, "please enter valid URL and your URL is - " + url);
		} catch (Exception e) {
			et.log(Status.FAIL, "URL is not hitted so first check URL here your exception is - " + e.getMessage());
		}
	}


	


	public void type(WebElement we, String sendValue, String elementName) {
		try {
			we.sendKeys(sendValue);
			et.log(Status.INFO, "Data entered successfuly in " + elementName + " here Data is - " + sendValue);

		} catch (ElementNotInteractableException e) {
			try {
			type_JS(we, sendValue, elementName);
			et.log(Status.INFO, "Data entered successfuly throug javaScript in " + elementName + " here Data is - " + sendValue);

			}catch (Exception e1) {
				et.log(Status.FAIL, "This is a Exception " + e1.getMessage());
				e1.printStackTrace();
				
			}
		} catch (StaleElementReferenceException e) {
			try {
			we.sendKeys(sendValue);
			}catch (Exception e1) {
				et.log(Status.FAIL, "This is a Exception " + e1.getMessage());
				e1.printStackTrace();

			}
			et.log(Status.FAIL, "This is StaleElementReferenceException");

		} catch (InvalidElementStateException e) {
			et.log(Status.FAIL, "This is InvalidElementStateException");

		} catch (InvalidArgumentException e) {
			et.log(Status.FAIL, "This is InvalidArgumentException");

		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
			e.printStackTrace();
		}

	}


	public void click(WebElement we, String elementName) {
		try {
			we.click();

			et.log(Status.INFO, elementName + " is clicked successfully");
		} catch (ElementClickInterceptedException e) {
			try {
				click_JS(we, elementName);
				et.log(Status.INFO, elementName + " is clicked successfully");
				}catch (Exception e1) {
					et.log(Status.FAIL, "This is a Exception " + e1.getMessage());
				}			
		} catch (ElementNotInteractableException e) {
			try {
				click_JS(we, elementName);
				et.log(Status.INFO, elementName + " is clicked successfully");
				}catch (Exception e1) {
					et.log(Status.FAIL, "This is a Exception " + e1.getMessage());
				}
		} catch (Exception e) {
			et.log(Status.FAIL, "unknown exception " + e.getMessage());
		}

	}


	public String getInnerText(WebElement we, String elementName) {
		String innerT = null;
		try {
			innerT = we.getText();
			et.log(Status.INFO, elementName + " innter text is founded successfully which is - " + innerT);
		} catch (NullPointerException e) {
			innerT = we.getText();
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());

		} catch (ElementNotInteractableException e) {

			et.log(Status.FAIL, "This is element not interactable exception");

		} catch (StaleElementReferenceException e) {

			et.log(Status.FAIL, "This is StaleElementReferenceException");

		} catch (InvalidElementStateException e) {
			et.log(Status.FAIL, "This is InvalidElementStateException");

		} catch (InvalidArgumentException e) {
			et.log(Status.FAIL, "This is InvalidArgumentException");

		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}
		return innerT;

	}


	public String getAttributeValue(WebElement we, String attributeName, String elementName) {

		String attValue = null;
		try {
			attValue = we.getAttribute(attributeName);
			et.log(Status.INFO, elementName + " name/attribute_Value is " + attValue);
		} catch (NullPointerException e) {
			attValue = we.getAttribute(attributeName);
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());

		} catch (ElementNotInteractableException e) {

			et.log(Status.FAIL, "This is element not interactable exception");

		} catch (StaleElementReferenceException e) {

			et.log(Status.FAIL, "This is StaleElementReferenceException");

		} catch (InvalidElementStateException e) {
			et.log(Status.FAIL, "This is InvalidElementStateException");

		} catch (InvalidArgumentException e) {
			et.log(Status.FAIL, "This is InvalidArgumentException");

		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}
		return attValue;

	}


	public Actions actionsObj() {
		Actions ac = null;
		try {
			ac = new Actions(driver);
		} catch (Exception e) {
			et.log(Status.FAIL, " this is a Exception " + e.getMessage());
		}
		return ac;

	}


	public void mouseOverActions(WebElement we, String elementName) {
		try {
			actionsObj().moveToElement(we).build().perform();
			et.log(Status.INFO, " This mouse focus is conciderd origin to the element which name is - " + elementName
					 );
		} catch (Exception e) {
			et.log(Status.FAIL, " this is a Exception " + e.getMessage());
		}
	}


	public void clickActions(WebElement we, String elementName) {
		try {
			actionsObj().click(we).build().perform();
			et.log(Status.INFO, " The element - " + elementName + " is Clicked Successfullly");
		} catch (Exception e) {
			et.log(Status.FAIL, " this is a Exception " + e.getMessage());
		}
	}


	public void doubleClickActions(WebElement we, String elementName) {

		try {
			actionsObj().doubleClick(we).build().perform();
			et.log(Status.INFO, "Double clicked successfully on - " + elementName);
		} catch (Exception e) {
			et.log(Status.FAIL, " this is a Exception " + e.getMessage());
		}
	}


	public void typeActions(WebElement we, String enterDataInTextBox, String elementName) {
		try {
			actionsObj().sendKeys(we, enterDataInTextBox).build().perform();
			;
			et.log(Status.INFO, "  In The element - " + elementName + " Data is sended Successfullly and data is - "
					+ enterDataInTextBox);
		} catch (Exception e) {
			et.log(Status.FAIL, " this is a Exception " + e.getMessage());
		}
	}


	public void scrollToElementActions(WebElement we, String elementName) {
		try {
			actionsObj().scrollToElement(we).build().perform();
			et.log(Status.INFO, "Scrolled successfully till the elemetnd - " + elementName);
		} catch (Exception e) {
			et.log(Status.FAIL, " this is a Exception " + e.getMessage());
		}

	}


	public void dragAndDropActions(WebElement weSource, WebElement weTarget, String elementName) {

		try {
			actionsObj().dragAndDrop(weSource, weTarget).release().build().perform();
			et.log(Status.INFO, "For the element " + elementName + " Drag and Drop done Successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, " this is a Exception " + e.getMessage());
		}

	}


	public Select selectObj(WebElement we, String elementName) {

		Select select = null;
		try {
			select = new Select(we);
			et.log(Status.INFO, "Select class Object is created successfully for the element - " + elementName);
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}
		return select;

	}


	public void selectByIndex(WebElement we, String elementName, int enterIndexNumber) {
		try {
			selectObj(we, elementName).selectByIndex(enterIndexNumber);
			et.log(Status.INFO,
					getFirstSelectedOptionText(we, elementName) + " this Opetion is selected successfully ");

		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}

	}


	public void selectByVisibleText(WebElement we, String elementName, String enterVisibleText) {

		try {
			selectObj(we, elementName).selectByVisibleText(enterVisibleText);
			et.log(Status.INFO,
					getFirstSelectedOptionText(we, elementName) + " this Opetion is selected successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}

	}


	public void selectByValue(WebElement we, String elementName, String enterValueAttributeValue) {

		try {
			selectObj(we, elementName).selectByValue(enterValueAttributeValue);
			et.log(Status.INFO,
					getFirstSelectedOptionText(we, elementName) + " this Opetion is selected successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}

	}


	public String getFirstSelectedOptionText(WebElement we, String elementName) {
		String firstOption = null;
		try {
			firstOption = selectObj(we, elementName).getFirstSelectedOption().getText();
			et.log(Status.INFO, "For " + elementName + " Now Current Selected Optins is - " + firstOption);
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}
		return firstOption;
	}


	public List<WebElement> mygetAllOptionsOfDropDown(WebElement we, String elementName) {
		List<WebElement> allOption = null;
		try {
			allOption = selectObj(we, elementName).getOptions();
			et.log(Status.INFO, "For " + elementName + " found all element Successfully and total element is - "
					+ allOption.size());
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, elementName + " not found and this is a Exception " + e.getMessage());
		}
		return allOption;
	}


	public boolean getEnableStatusOfElement(WebElement we, String elementName) {
		boolean b = false;
		try {
			b = we.isEnabled();
			if (b == true) {
				et.log(Status.INFO, elementName + " is Enabled ");
			} else {
				et.log(Status.INFO, elementName + " is Not Enable ");
			}

		} catch (NullPointerException e) {

			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());

		} catch (Exception e) {
			et.log(Status.FAIL, elementName + " is Not Enable and exception is - " + e.getMessage());
		}
		return b;

	}


	public void switchToFrame(int enterIndexNumber) {
		try {
			driver.switchTo().frame(enterIndexNumber);
			et.log(Status.INFO, "Frame is switched successfully");
		} catch (Exception e) {
			et.log(Status.FAIL, " Exception while Switching the frame and exception is - " + e.getMessage());
		}
	}


	public void switchToFrame(String enterName_Id) {

		try {
			driver.switchTo().frame(enterName_Id);
			et.log(Status.INFO, "Frame is switched successfully");
		} catch (Exception e) {
			et.log(Status.FAIL, " Exception while Switching the frame and exception is - " + e.getMessage());
		}
	}


	public void switchToFrame(WebElement we, String frameName) {
		try {
			driver.switchTo().frame(we);
			et.log(Status.INFO, "Frame is switched successfully and frame name is - " + frameName);
		} catch (Exception e) {
			et.log(Status.FAIL, " Exception while Switching the frame and exception is - " + e.getMessage());
		}

	}


	public void switchToMainWindowFromFrame() {
		try {
			driver.switchTo().defaultContent();
			et.log(Status.INFO, "Frame is switched on default successfully");
		} catch (Exception e) {
			et.log(Status.FAIL,
					" Exception while Switching the frame as a default and exception is - " + e.getMessage());
		}
	}


	public void switchToPriviousWidnowFromFrame() {
		try {
			driver.switchTo().parentFrame();
			et.log(Status.INFO, "Frame is switched on Parent frame successfully");
		} catch (Exception e) {
			et.log(Status.FAIL, " Exception while Switching the parent frame and exception is - " + e.getMessage());
		}
	}

   
	public Alert alertClassObj() {
		Alert al = null;
		try {
			al = driver.switchTo().alert();
		} catch (NullPointerException e) {
			et.log(Status.INFO, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, " Exceptin while creating Alert class object and exception is - " + e.getMessage());
		}
		return al;
	}


	//++++++++++++++++++ getAlertText +++++++++++++++++++++++++++++++++++++++++++

	public String getAlertText() {
		String alertMessage = null;
		try {
			Alert al = driver.switchTo().alert();
			alertMessage = al.getText();
			et.log(Status.INFO, "Alert message is found successfully here the alert message is - " + alertMessage);
		} catch (Exception e) {
			et.log(Status.FAIL, " Exception while getting Text of alert and exception is - " + e.getMessage());
		}
		return alertMessage;
	}


	public void acceptAlert() {
		try {
			alertClassObj().accept();
			et.log(Status.INFO, "Alert is accepted Successfully");
		} catch (Exception e) {
			et.log(Status.FAIL, " Exception while accepting alert and exception is - " + e.getMessage());
		}
	}


	public void dismissAlert() {
		try {
			alertClassObj().dismiss();
			et.log(Status.INFO, "Alert is Dismissed successfully");
		} catch (Exception e) {
			et.log(Status.FAIL, " Exception while Dismissing alert and exception is - " + e.getMessage());
		}
	}

//++++++++++++++ mySwitchToWindow_WithoutcloseRemainingTab +++++++++++++++++++	

	public void switchToWindow_WithoutcloseRemainingTab(String titleOrURL_full) {
		try {
			Set<String> whs = driver.getWindowHandles();
			for (String wh : whs) {
				driver.switchTo().window(wh);
				if (titleOrURL_full.equalsIgnoreCase(getCurrentWindowTitle())) {
					et.log(Status.INFO, "Window focus is switched successfully on this Title - " + titleOrURL_full);
					break;
				} else if (titleOrURL_full.equalsIgnoreCase(getCurrentWindowURL())) {
					et.log(Status.INFO, "Window focus is switched successfully on this URL - " + titleOrURL_full);
					break;
				}

			}
		} catch (NoSuchWindowException e) {
			et.log(Status.FAIL, "no such window exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, " for the window the exception is - " + e.getMessage());
		}
	}




	public void SwitchToWindow_Contains(String titleOrURL_contains) {
		try {
			Set<String> whs = driver.getWindowHandles();
			for (String wh : whs) {
				driver.switchTo().window(wh);
				if (titleOrURL_contains.contains(getCurrentWindowTitle())) {
					et.log(Status.INFO, "Window focus is switched successfully on this Title - " + titleOrURL_contains);
					break;
				} else if (titleOrURL_contains.contains(getCurrentWindowURL())) {
					et.log(Status.INFO, "Window focus is switched successfully on this URL - " + titleOrURL_contains);
					break;
				}

			}
		} catch (NoSuchWindowException e) {
			et.log(Status.FAIL, "no such window exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, " for the window the exception is - " + e.getMessage());
		}
	}

//++++++++++++++++++ takeScreenShot +++++++++++++++++++++++++++++++++++++++++

	public void takeScreenShot(String elementName) {
		try {
            String screenshotPath="C:\\Users\\HP\\Desktop\\New folder\\FrameWorkScreenShot\\" + elementName + ".png";
			TakesScreenshot tss = (TakesScreenshot) driver;
			File mySS = tss.getScreenshotAs(OutputType.FILE);
			File locationOFSS = new File(
					screenshotPath);
			try {
				Files.copy(mySS, locationOFSS);
				et.log(Status.INFO, "Screenshot is taken successfully ");
			} catch (IOException e) {
				e.printStackTrace();
			}
			et.addScreenCaptureFromPath(screenshotPath);
			
		} catch (Exception e) {
			et.log(Status.FAIL, "Screenshot was not taken and Exception is - " + e.getMessage());
		}
		
	}


	public void UploadFile(WebElement we, String fileName, String filePath) {
		try {
			type(we, filePath, fileName);
			et.log(Status.INFO, "File uploaded successfully and file name is - " + fileName);
		} catch (Exception e) {
			et.log(Status.INFO, " Exception while uploading File and exception is - " + e.getMessage());
		}

	}


	public void click_JS(WebElement we, String elementName) {
		try {
			myjavaScriptObj().executeScript("arguments[0].click();", we);
			et.log(Status.INFO, "Clicked through javaSecript on " + elementName + " Successfully");
		} catch (ElementClickInterceptedException e) {
			et.log(Status.FAIL, "Element not found due to page not alighn properly");
		} catch (ElementNotInteractableException e) {
			et.log(Status.FAIL, "Element not found");
		} catch (Exception e) {
			et.log(Status.FAIL, "unknown exception " + e.getMessage());
		}

	}


	public void type_JS(WebElement we, String sendValue, String elementName) {

		try {
			myjavaScriptObj().executeScript("arguments[0].value=" + sendValue + ";", we);
			et.log(Status.INFO,
					"Throung JavaScript Data entered successfuly in " + elementName + " here Data is - " + sendValue);

		} catch (ElementNotInteractableException e) {

			et.log(Status.FAIL, "This is element not interactable exception");

		} catch (StaleElementReferenceException e) {

			et.log(Status.FAIL, "This is StaleElementReferenceException");

		} catch (InvalidElementStateException e) {
			et.log(Status.FAIL, "This is InvalidElementStateException");

		} catch (InvalidArgumentException e) {
			et.log(Status.FAIL, "This is InvalidArgumentException");

		} catch (Exception e) {
			et.log(Status.FAIL, "This is a Exception " + e.getMessage());
		}

	}


	public JavascriptExecutor myjavaScriptObj() {
		JavascriptExecutor jse = null;
		try {
			jse = (JavascriptExecutor) driver;
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, " exception is - " + e.getMessage());
		}
		return jse;

	}

	
	public void scrollToElement_JS(WebElement we, String elementName) {
         //  String ElementName =we.getAccessibleName();
           try {
			myjavaScriptObj().executeScript("arguments[0].scrollIntoView();", we);
			et.log(Status.INFO, "Window scrolled to " + elementName + " successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL,
					"Scroll to " + elementName + " is not performed and this is a Exception " + e.getMessage());
		}

	}


	public void scrollToBottom_JS() {

		try {
			myjavaScriptObj().executeScript("window.scrollBy(0,document.body.scrollHeight)");
			et.log(Status.INFO, "Window scrolled to bottom successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "Scroll to bottom is not performed and this is a Exception " + e.getMessage());
		}

	}


	public void myScrollByJavaScript(int Xaxis, int Yaxis) {

		JavascriptExecutor jse = myjavaScriptObj();
		try {
			jse.executeScript("window.scrollBy(" + Xaxis + "," + Yaxis + ")");
			et.log(Status.INFO, "window is scrolled successfully through JS");
		} catch (Exception e) {
			et.log(Status.FAIL, "window is not scrolled and exception is - " + e.getMessage());
		}
	}


	public String getCurrentWindowTitle() {
		String title = null;
		try {
			title = driver.getTitle();
			et.log(Status.INFO, "my current title is - " + title);
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "this is a null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "this is exception " + e.getMessage());
		}
		return title;
	}


	public String getCurrentWindowURL() {
		String currentURL = null;
		try {
			currentURL = driver.getCurrentUrl();
			et.log(Status.INFO, "my current URL is - " + currentURL);
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "this is a null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "this is exception " + e.getMessage());
		}
		return currentURL;
	}

//+++++++++++++++++++++ refreshPage +++++++++++++++++++++++++++++++++++
	public void refreshPage() {
		try {
			driver.navigate().refresh();
			et.log(Status.INFO, "Page is refreshed successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "Page is not refreshed and exception is - " + e.getMessage());
		}
	}

//+++++++++++++++++++++ goBack ++++++++++++++++++++++++++++++++++++++++

	public void goBack() {
		try {
			driver.navigate().back();
			et.log(Status.INFO, "Clicked on Back Option successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "Back Option is not pressed and exception is - " + e.getMessage());
		}
	}


	public void goForword() {
		try {
			driver.navigate().forward();
			et.log(Status.INFO, "Clicked on forword option successfully ");
		} catch (Exception e) {
			et.log(Status.FAIL, "Forword Option is not pressed and exception is - " + e.getMessage());
		}
	}


	public List<WebElement> getWebElementsList(By locator, String elementsName) {

		List<WebElement> a = null;
		try {
			a = driver.findElements(locator);
			et.log(Status.INFO, "found all" + elementsName + " Elements successfully ");
		} catch (NullPointerException e) {

			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());

		} catch (NoSuchElementException e) {
			et.log(Status.FAIL, "This is the NoSuchelement Exception " + e.getMessage());

		} catch (InvalidSelectorException e) {
			et.log(Status.FAIL,
					"This is your wrong xpath syntax please check it again and correct it " + e.getMessage());

		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}
		return a;
	}
	
	
	public void verifyInnerText(WebElement we, String elementName, String expectedText) {
		String actualText = null;
		try {
			
			actualText = getInnerText(we, elementName);

			if (actualText.equals(expectedText)) {

				et.log(Status.PASS, "Actual text is matched with expected successfully here Actual is -" + actualText
						+ " and Expected is - " + expectedText);
			} else {

				et.log(Status.FAIL, "Actual text is Not matched with expected here Actual is -" + actualText
						+ " and Expected is - " + expectedText);
				takeScreenShot(elementName);

			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}

	}

// +++++++++++++++++++++ verifyAttributeValue +++++++++++++++++++++++++++++++++++

	public void verifyAttributeValue(WebElement we, String attributeName, String elementName, String expectedAttributeValue) {

		String actualAttributeValue = null;
		try {

			actualAttributeValue = getAttributeValue(we, attributeName, elementName);

			if (actualAttributeValue.equals(expectedAttributeValue)) {

				et.log(Status.PASS,
						"Actual attributeValue is matched successfully with expected AttributeValue here Actual is -"
								+ actualAttributeValue + " and Expected is - " + expectedAttributeValue);
			} else {

				et.log(Status.FAIL, "Actual attributeValue is Not matched with expected AttributeValue here Actual is -"
						+ actualAttributeValue + " and Expected is - " + expectedAttributeValue);

			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}

	}


	public void verifyPageTitle(String expectedTitle) {
		String actualTitle = null;
		try {
			actualTitle = getCurrentWindowTitle();

			if (actualTitle.equals(expectedTitle)) {

				et.log(Status.PASS, "Actual title is matched with expected title successfully here Actual title is -"
						+ actualTitle + " and Expected title is - " + expectedTitle);
			} else {

				et.log(Status.FAIL, "Actual title is Not matched with expected title here Actual title is -"
						+ actualTitle + " and Expected title is - " + expectedTitle);

			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}

	}


	public void verifyEnabled(WebElement we, String elementName) {

		Boolean isEnabled = null;
		try {

			isEnabled = getEnableStatusOfElement(we, elementName);
			if (isEnabled) {

				et.log(Status.PASS, elementName + " is enabled / clickable now so you can perform anything");

			} else {
				et.log(Status.FAIL, elementName + " is Not enabled / clickable now so you can't perform anything");
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}

	}


	public void verifyDisabled(WebElement we, String elementName) {
		boolean isEnabled = false;
		try {
			isEnabled = getEnableStatusOfElement(we, elementName);
			if (isEnabled == false) {
				et.log(Status.PASS, elementName + " is Not enabled / clickable now so you can't perform anything");

			} else {
				et.log(Status.FAIL, elementName + " is enabled / clickable now so you can perform anything");
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}
	}

// +++++++++++++++++++++ verifyVisible ++++++++++++++++++++++++++++++++++++++

	public void verifyVisible(WebElement we, String elementName) {
		boolean isDisplayed = false;
		try {
			isDisplayed = we.isDisplayed();
			if (isDisplayed) {

				et.log(Status.PASS, elementName + " is visible on page successfully");

			} else {
				et.log(Status.FAIL, elementName + " is Not visible on page");
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}
	}


	public void verifyInvisible(WebElement we, String elementName) {

		
		try {
			boolean actualDisplayStatus = we.isDisplayed();
			if (actualDisplayStatus == false) {
				et.log(Status.PASS, elementName + " is Not Visible on page Successfully");

			} else {
				et.log(Status.FAIL, elementName + " is Visible on page");
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}
	}

//++++++++++++++++++++ verifyCheckBox_RadioButtonSelected +++++++++++++++++++++++

	public void verifyCheckBox_RadioButtonSelected(WebElement we, String elementName) {
		boolean isSelected = false;
		try {
			isSelected = we.isSelected();

			if (isSelected) {

				et.log(Status.PASS, elementName + " is already selected/Checked successfully");

			} else {
				et.log(Status.FAIL, elementName + " is Not selected/Checked");
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}
	}


	public void verifyCheckBox_RadioButtonUnSelected(WebElement we, String elementName) {
		boolean isDisplayed = false;
		try {
			isDisplayed = we.isDisplayed();
			if (isDisplayed == false) {
				et.log(Status.PASS, elementName + " is Not selected/Checked");

			} else {
				et.log(Status.FAIL, elementName + "is already selected/Checked");
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}

	}


	public void verifyFirstSelectedTextInDropDown(WebElement we, String elementName, String expectdSelectedOption) {
		String actualfirstSelectedOption = null;
		try {
			actualfirstSelectedOption = getFirstSelectedOptionText(we, elementName);
			if (actualfirstSelectedOption.equals(expectdSelectedOption)) {

				et.log(Status.PASS, "For the " + elementName + " first selected Option is matched successfully here"
						+ " expected is -" + expectdSelectedOption + " and actual is - " + actualfirstSelectedOption);

			} else {

				et.log(Status.FAIL, "For the " + elementName + " first selected Option is Not matched here"
						+ " expected is -" + expectdSelectedOption + " and actual is - " + actualfirstSelectedOption);
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}
	}


	public void verifyElementCount(By locator, String elementName, int expectedCountOfAllElement) {
		List<WebElement> allElement = null;
		try {
			allElement = getWebElementsList(locator, elementName);
			int actualCountOfElement = allElement.size();
			if (actualCountOfElement == expectedCountOfAllElement) {
				et.log(Status.PASS,
						"Actual element count is matched with Expected Count of element here actual count of element is - "
								+ actualCountOfElement + " and expected count of element is - "
								+ expectedCountOfAllElement);
			} else {
				et.log(Status.FAIL,
						"Actual element count is Not matched with Expected Count of element here actual count of element is - "
								+ actualCountOfElement + " and expected count of element is - "
								+ expectedCountOfAllElement);
			}
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
		}
	}
	
	//++++++++++++++++++++++++ verifyPopupInnerText ++++++++++++++++++++++++++++

		public void verifyPopupInnerText(String expectdAlertMessage) {
			try {
				String actualAlertMessage = getAlertText();
				if (actualAlertMessage.contains(expectdAlertMessage)) {
					et.log(Status.PASS,
							"Alert message is matched successfully and expected matched with actual here expected is - "
									+ expectdAlertMessage + " and actual is - " + actualAlertMessage);
				} else {
					et.log(Status.FAIL, "Expected alert message is Not matched with actual here expected is - "
							+ expectdAlertMessage + " and actual is - " + actualAlertMessage);
				}
				
			} catch (Exception e) {
				et.log(Status.FAIL, "This is Exception occured " + e.getMessage());
			}

		}


	public ChromeOptions ChromeOptionObject() {
		ChromeOptions co = null;
		try {
			co = new ChromeOptions();
//			et.log(Status.INFO, "Chrome Option is created successfully ");
		} catch (NullPointerException e) {
			et.log(Status.FAIL, "This is null pointer exception while createing chromeOptions " + e.getMessage());
		} catch (Exception e) {
			et.log(Status.FAIL, "ChromeOptions is not created and exception is - " + e.getMessage());
		}
		return co;

	}
	
	
	public void staticWait(int timeInSeconds) {
		
		try {
			Thread.sleep(timeInSeconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	/////// explicitWait
	/////// /////////////////////////////////////////////////////////////////////////////////

	// waitForVisibileity

	// waitForInvisiblity

	// waitForTextInElement

	// waitForElementEnabled

	// watiForElementDisabled
	
	
	////  Synchronization 
	
	////   Wait   -  static wait  -  Thread.sleep
	
	///dynamic wait  
	///  -  implicit wait  - 1 condition -  presence of element in html 
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	
	//explicit Wait  - 
	// 1)  explicitly add
	// 2)  you can mention your condition for which you want to wait
	     ///  presence in html, visibility , invisibility, enabled, disabled , 
	
	/// fluent wait 
	
	public void waitForElementVisibility(WebElement we, int maxTimeout) {
		
		WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(maxTimeout));
		wt.until(ExpectedConditions.visibilityOf(we));
		
		
	}
	
      public void waitForElementInvisibility(WebElement we, int maxTimeout) {
		
		WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(maxTimeout));
		wt.until(ExpectedConditions.invisibilityOf(we));
		
		
	}
	
	public void waitForElementEnabled(WebElement we, int maxTimeout) {
		
		WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(maxTimeout));
		wt.until(ExpectedConditions.elementToBeClickable(we));
		
		
	}
	
	public void waitForElementDisabled(WebElement we, int maxTimeout) {
		
		WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(maxTimeout));
		wt.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(we)));
		
		
	}	
	public void waitForTextInElement(WebElement we, int maxTimeout, String text) {
		
		WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(maxTimeout));
		wt.until(ExpectedConditions.textToBePresentInElement(we, text));
		
		
	}	
	
	///  ajax 
	public void fluentWait(int timeout) {
		
		FluentWait flt=new FluentWait<WebDriver>(driver);
		flt.pollingEvery(Duration.ofMillis(100));
		flt.withTimeout(Duration.ofSeconds(timeout));
		flt.ignoring(NoSuchElementException.class);
		
	}
	
	
	
	
	
	

}