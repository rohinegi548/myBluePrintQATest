package monkeyuserApp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import helpers.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;

public class StoreTests {

	WebDriver driver;

	@BeforeSuite
	@Description("Setting up Browser session")
	public void setup() throws IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(Utilities.getGlobalProperty("baseURL"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	@Description("Verifying if user can add a product to cart")
	public void AddAnItemToCart() throws InterruptedException {

		// Adding first available item to cart
		WebElement store_lnk = driver.findElement(By.xpath("//span[contains(text(),'Buy Plushies')]/ancestor::a"));
		store_lnk.click();

		WebElement item = driver.findElement(By.xpath("//li[starts-with(@class,'grid__item')]//a"));
		item.click();

		WebElement add_to_cartBtn = driver
				.findElement(By.xpath("//span[contains(text(),'Add to cart')]/ancestor::button"));
		add_to_cartBtn.click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='update']")).isDisplayed(),
				"Either user not able to add to cart or Cart page not loaded");

	}

	@Test(priority = 1)
	@Description("Verifying if Checkout Screen works")
	public void proceedToCheckout() throws InterruptedException {
		
		//checkingout for added item in cart
		WebElement checkoutBtn = driver.findElement(By.xpath("//input[@name='checkout']"));
		checkoutBtn.click();

		Utilities.waitUntilPageContainsText("Contact information", driver);

		driver.findElement(By.id("checkout_email_or_phone")).sendKeys("test.gmail@gmail.com");
		driver.findElement(By.id("checkout_shipping_address_last_name")).sendKeys("Armstrong");
		driver.findElement(By.id("checkout_shipping_address_address1")).sendKeys("1344/7");
		driver.findElement(By.id("checkout_shipping_address_zip")).sendKeys("12345");
		driver.findElement(By.id("checkout_shipping_address_city")).sendKeys("NY");

		WebElement ContToShipBtn = driver
				.findElement(By.xpath("//span[contains(text(),'Continue to shipping')]/ancestor::button"));

		ContToShipBtn.click();

		Assert.assertTrue(Utilities.waitUntilPageContainsText("Continue to payment", driver),
				"Checkout Screen not working");

	}

	@AfterSuite
	@Description("Closing the session")
	public void teardown() {

		driver.quit();
	}

}
