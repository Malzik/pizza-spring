package pizza.spring.service.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PizzaSpringCommandeAcceptanceTest {

	private WebDriver webDriver;

	@Before
	public void createWebDriver() {
		webDriver = new FirefoxDriver();
	}

	@After
	public void closeWebDriver() {
		webDriver.quit();
	}

	@Test
	public void laCommandeEstValideSiTousLesChampsSontRemplis() throws Exception {
		ResultPage resultPage = HomePage.openWith(webDriver)
										.selectPizza("pizzaId", "//option[. = 'Margherita']")
				                        .enterKeywords("nom", "test")
				                        .enterKeywords("telephone", "0123456789")
				                        .clickOnCommander();

		assertTrue(resultPage.isIdPresent("recap-commande"));
	}

	@Test
	public void laCommandeEchoueSiAucunePizzaNEstSelectionee() throws Exception {
		ResultPage resultPage = HomePage.openWith(webDriver)
				.enterKeywords("nom", "test")
				.enterKeywords("telephone", "0123456789")
				.clickOnCommander();

		assertTrue(resultPage.isIdPresent("pizzaId.errors"));
		assertEquals("Vous devez choisir au moins une pizza", resultPage.getValueForId("pizzaId.errors"));
	}

	@Test
	public void laCommandeEchoueSiAucunNumeroDeTelephoneNEstRenseigne() throws Exception {
		ResultPage resultPage = HomePage.openWith(webDriver)
				.selectPizza("pizzaId", "//option[. = 'Margherita']")
				.enterKeywords("nom", "test")
				.clickOnCommander();

		assertTrue(resultPage.isIdPresent("telephone.errors"));
		assertEquals("ne peut pas être vide", resultPage.getValueForId("telephone.errors"));
	}
}
