package core;

import static utils.DriverUtils.switchToDefaultPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import utils.Constants;
import utils.DriverUtils;
import utils.Report;

public class DriverActions {

	protected WebDriver driver;

	/**
	 * Initialize base class constructor with driver.
	 * @param driver
	 */
	public DriverActions(WebDriver driver) {
		this.driver = driver;	
	}

	protected void click(String locator, String locatorType, String objectName) {
		if(driver != null)
			System.out.println("Not Null");
		else
			System.out.println("Null");
		try {
			WebElement element = getWebElement(locator, locatorType);
			if (element != null) {
				element.click();
				DriverUtils.waitImplicit(driver, Constants.WAIT_IN_SECONDS_5);
				Report.pass("'" + objectName + "'" + " is clicked");
			} else {
				Report.fail("'" + objectName + "' is not found");
			}
		} catch (StaleElementReferenceException e) {
			Report.fail("'" + objectName + "' is not found");
		} catch (WebDriverException e) {
			Report.fail("Unable to click " + "'" + objectName + "'");
		}
	}


	protected WebElement getWebElement(String locator, String locatorType) {

		List<WebElement> elements = getElements(locator, locatorType);
		if (!elements.isEmpty()) {
			return elements.get(0);
		}

		switchToDefaultPage(driver);
		elements = getElements(locator, locatorType);

		if (!elements.isEmpty()) {
			return elements.get(0);
		} else {
			return switchToFrameAndReturnElement(locator, locatorType);
		}
	}

	protected List<WebElement> getElements(String locator, String locatorType) {

		List<WebElement> elements = new ArrayList<WebElement>();
		switch (locatorType) {

		case "xpath":
			elements = driver.findElements(By.xpath(locator));
			break;
		case "css":
			elements = driver.findElements(By.cssSelector(locator));
			break;
		case "name":
			elements = driver.findElements(By.name(locator));
			break;
		case "id":
			elements = driver.findElements(By.id(locator));
			break;
		case "tagname":
			elements = driver.findElements(By.tagName(locator));
			break;
		default:
			elements = null;
			Report.info("Invalid locator type...Please check");
			break;
		}
		return elements;	
	}


	protected WebElement switchToFrameAndReturnElement(String locator, String locatorType) {

		int index = 0;
		List<WebElement> frameElements;

		frameElements = getElements("iframe", "tag name");
		// driver.findElements(By.tagName("iframe"));
		Iterator<WebElement> itr = frameElements.iterator();

		while (itr.hasNext()) {
			try {
				driver.switchTo().frame(index);

				List<WebElement> elements = getElements(locator, locatorType);
				if (!elements.isEmpty()) {
					return elements.get(0);
				} else {
					return switchToFrameAndReturnElement(locator, locatorType);
				}
			} catch (NoSuchFrameException e) {
				switchToDefaultPage(driver);
			}
			itr.next();
		}
		return null;
	}

	protected WebElement getElement(String locator, String locatorType) {
		try {
			DriverUtils.waitExplicit(Constants.WAIT_IN_SECONDS_2);
			List<WebElement> element = getElements(locator, locatorType);
			return element.get(0);
		} catch (NoSuchElementException e) {
			return null;
		}
	}


}

