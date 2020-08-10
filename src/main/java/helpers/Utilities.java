package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	public static String getGlobalProperty(String key) throws IOException {
		
		//loading base url from the property file
		Properties p = new Properties();
		FileInputStream fs = new FileInputStream("src\\test\\java\\resources\\global.properties");
		p.load(fs);

		return p.getProperty(key);
	}

	public static void waitForElementVisiblility(WebElement elmt, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(elmt));
	}

	public static void waitForElementsVisiblility(List<WebElement> elmt, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElements(elmt));
	}
	
	public static boolean waitUntilPageContainsText(String text, WebDriver driver){
		
		return driver.getPageSource().contains(text);
		
	}

}
