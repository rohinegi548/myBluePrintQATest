package monkeyuserApp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import helpers.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;

public class ComicTests {

	WebDriver driver;

	@BeforeSuite
	@Description("Setting up Browser session")
	public void setup() throws IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeMethod
	@Description("Opening chrome browser")
	public void OpenBaseURL() throws IOException {

		driver.manage().window().maximize();
		driver.navigate().to(Utilities.getGlobalProperty("baseURL"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test(priority = 0)
	@Description("Verifying user can view a comic")
	public void ViewsARandomComic() throws InterruptedException {

		WebElement rand_comic_link = driver.findElement(By.xpath("//img[@title='random']/ancestor::a"));
		rand_comic_link.click();

		Thread.sleep(5000);
		WebElement content_img = driver.findElement(By.xpath("//div[@class='content']/p/img"));
		Utilities.waitForElementVisiblility(content_img, driver);
		Assert.assertTrue(content_img.isDisplayed(), "No Random Comic is available, please try again");

	}

	@Test(priority = 1)
	@Description("Verifying user can view Latest comic")
	public void ViewsTheLatestComic() throws InterruptedException {

		WebElement latest_comic_link = driver
				.findElement(By.xpath("//img[@title='fresh out of the oven']/ancestor::a"));
		latest_comic_link.click();

		Thread.sleep(5000);

		WebElement content_img = driver.findElement(By.xpath("//div[@class='content']/p/img"));
		Utilities.waitForElementVisiblility(content_img, driver);
		Assert.assertTrue(content_img.isDisplayed(), "No Latest Comic is available, please try again");

	}

	@Test(priority = 3)
	@Description("Verifying user can view Next available comic")
	public void UserViewsTheNextComic() {

		WebElement first_comic_link = driver.findElement(By.xpath("//div[@title='how it all began']/a"));
		first_comic_link.click();
		WebElement next_comic_link = driver.findElement(By.xpath("//div[@title='next']/a"));
		next_comic_link.click();

		WebElement content_img = driver.findElement(By.xpath("//div[@class='content']/p/img"));
		Utilities.waitForElementVisiblility(content_img, driver);
		Assert.assertTrue(content_img.isDisplayed(), "No Next Comic is available, please try again");

	}

	@Test(priority = 4)
	@Description("Verifying user can view comic History by years")
	public void UserViewsHistoryOFComics() {

		WebElement comiclist_link = driver.findElement(By.linkText("COMICS LIST"));
		comiclist_link.click();

		List<WebElement> comic_history_years = driver.findElements(By.xpath("//div[@class='toc-year']"));
		Utilities.waitForElementsVisiblility(comic_history_years, driver);
		Assert.assertTrue(comic_history_years.size() >= 1, "Comic history not loaded!");

	}

	@Test(priority = 5, dataProvider = "comicsForDates")
	@Description("Verifying user can view a comic for Supplied Date")
	public void OpenComicForDates(String Date) {

		WebElement comiclist_link = driver.findElement(By.linkText("COMICS LIST"));
		comiclist_link.click();

		List<WebElement> comic_for_date = driver
				.findElements(By.xpath("//strong[text()='" + Date + "']/following-sibling::div/a[1]"));

		if (comic_for_date.size() >= 1) {

			comic_for_date.get(0).click();
			WebElement content_img = driver.findElement(By.xpath("//div[@class='content']/p/img"));
			Utilities.waitForElementVisiblility(content_img, driver);
			Assert.assertTrue(content_img.isDisplayed(), "No Next Comic is available, please try again");
			Allure.addAttachment("Comic: " + content_img.getAttribute("alt"), "Viewed by user for date: " + Date);

		} else {

			Assert.fail("Comic is not available for date: " + Date);
		}

	}

	@AfterSuite
	@Description("Closing the session")
	public void teardown() {

		driver.quit();
	}

	// User can provide as many as dates to check the comic for particular date
	@DataProvider(name = "comicsForDates")
	public Object[][] getComicDates() {

		return new Object[][] { { "December  4, 2018" } // comic available for
														// this date
				, { "December  3, 2018" } // no comic available for this date
											// (test will fail)
				, { "June 19, 2018" } // comic available for this date
				, { "May 30, 2017" } // comic available for this date
		};
	}
}
