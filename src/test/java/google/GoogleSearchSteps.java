package google;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LoggerContext;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import fw.driverfactory.DriverManager;
import fw.driverfactory.DriverManagerFactory;
import fw.driverfactory.DriverManagerFactory.DriverType;
import fw.settings.Constants;
import fw.settings.Log;

public class GoogleSearchSteps {
	protected WebDriver driver;
	protected WebDriverWait wait;
	Log log = new Log();
	static DriverManager drvManager;

	@BeforeStory
	public static void setupClass() {
		System.setProperty("log4j.configurationFile", "test/resources/log4j2.xml");
		ThreadContext.put("logLocation", Constants.TEST_LOG);
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		ctx.reconfigure();
		drvManager = DriverManagerFactory.getManager(DriverType.CHROME);
	}

	@BeforeScenario
	public void beforeScenario() {
		driver = drvManager.getDriver();
		driver.manage().timeouts().setScriptTimeout(Constants.TIMEOUT_IN_SECOND, TimeUnit.SECONDS);
	}

	@AfterScenario
	public void afterScenario() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Given("that I open the Google page")
	public void givenThatIOpenTheGooglePage() {
		log.info("Open Google Page");
		driver.get("https://www.google.com/");
	}

	@When("I search for the value $searchString")
	public void whenIAddIntoTheSearchBoxTheValue(@Named("value") String searchString) {
		log.info("Enter search text [" + searchString + "]");
		WebElement txtGoogleSearchBox = driver.findElement(By.name("q"));
		txtGoogleSearchBox.sendKeys(searchString + Keys.ENTER);

	}

	@Then("I would see the search result text $expectString")
	public void thenIWouldSeeTheSearchResultTextWhatIsJBehave(@Named("text") String expectString) {
		List<WebElement> lstSearchResult = driver.findElements(By.cssSelector("div#rcnt div#search div.g"));
		int count = 0;
		for (WebElement result : lstSearchResult) {
			String getText = result.findElement(By.cssSelector("h3.DKV0Md")).getText();
			if (getText.equalsIgnoreCase(expectString)) {
				count++;
			}
		}
		if (count == 0) {
			log.fail("Not found the text [" + expectString + "] in search results");
		} else {
			log.pass("Found [" + count + "] result(s).");
		}
		assertTrue(count > 0);
	}
}