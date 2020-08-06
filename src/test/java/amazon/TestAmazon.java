package amazon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import core.DriverBase;
import listeners.TestListener;
import pages.AmazonPage;

@Listeners(TestListener.class)
public class TestAmazon extends DriverBase{

	AmazonPage amazon;
	
	@BeforeClass
	public void init(){
		amazon = new AmazonPage(driver);
	}
	
	@Test
	public void searchItem(){
		amazon.searchItem("mobile");
	}
	
	
	@AfterClass()
	public void tearDown() {

	}
}
