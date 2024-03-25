package epam.ui;

import com.epam.driversettings.WebDriverFactory;
import com.epam.services.listener.TestListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ExtendWith(TestListener.class)
public class BaseUiTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseUiTest.class);


    @AfterAll
    public static void tearDown() {
        LOGGER.info("tearDown");
        WebDriverFactory.quitDriver();
    }

}
