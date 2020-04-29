package pizza.spring.service.selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver webDriver;

	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public HomePage open() {
		webDriver.navigate().to("http://localhost:8080/pizza-spring/commande");
		assertTrue("Titre de page inattendu " + webDriver.getTitle(), webDriver.getTitle().startsWith("Pizza Spring"));
		return this;
	}

	public static HomePage openWith(WebDriver webDriver) {
		HomePage homePage = new HomePage(webDriver);
		homePage.open();
		return homePage;
	}

	public HomePage enterKeywords(String id, String... words) {
		WebElement searchInput = webDriver.findElement(By.id(id));
		searchInput.sendKeys(String.join(" ", words));
		return this;
	}

	public HomePage selectPizza(String id, String pizza) {
		WebElement searchInput = webDriver.findElement(By.id(id));
		searchInput.findElement(By.xpath(pizza)).click();
		return this;
	}

	public ResultPage clickOnCommander() {
		WebElement searchButton = webDriver.findElement(By.cssSelector("button"));
		searchButton.click();
		return new ResultPage(webDriver);
	}
}
