package epam.ui;

import com.epam.driversettings.WebDriverFactory;
import com.epam.services.listener.TestListener;
import com.epam.services.listener.TestNGListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@ExtendWith(TestListener.class)
@Listeners(TestNGListener.class)
public class BaseUiTest {

    @AfterMethod
    public void tearDownTestNg() {
        WebDriverFactory.quitDriver();
    }
}
