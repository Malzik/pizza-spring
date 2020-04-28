package pizza.spring.service.selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {

	private WebDriver webDriver;

	public ResultPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		assertTrue("Titre de page inattendu " + webDriver.getTitle(), webDriver.getTitle().endsWith("Pizza Spring"));
	}

	public boolean isLinkPresent(String link) {
		return ! webDriver.findElements(By.partialLinkText(link)).isEmpty();
	}

	public boolean isIdPresent(String id) {
		return webDriver.findElement(By.id(id)).isDisplayed();
	}

	public String getValueForId(String id) {
		return webDriver.findElement(By.id(id)).getText();
	}
}
