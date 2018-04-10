package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.BaseDriver;
import utils.InvalidBrowserException;

public class StartingSteps extends BaseSteps{

    @Before
    public void beforeScenario() throws InvalidBrowserException {
        driver = new BaseDriver().getDriver();
    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        driver.quit();
    }
}
