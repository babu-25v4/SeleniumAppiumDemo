package pages;

import org.openqa.selenium.WebDriver;

import core.DriverActions;
import utils.DriverUtils;

public class AmazonPage extends DriverActions{

	public AmazonPage(WebDriver driver) {
		super(driver);		
	}

	public void searchItem(String item){
		click("//a[@class='nav-logo-link' and @aria-label='Amazon']", "xpath", "Amazon logo");
		DriverUtils.waitExplicit(20);
	}
	
}
