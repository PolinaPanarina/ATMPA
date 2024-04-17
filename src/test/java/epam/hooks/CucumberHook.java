package epam.hooks;

import com.epam.driversettings.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CucumberHook {
    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberHook.class);

    @Before
    public void setUpCucumberTest(){
        LOGGER.info("Setting up cucumber test");
    }

    @After
    public void closeBrowser() {
        WebDriverFactory.quitDriver();
    }

}
