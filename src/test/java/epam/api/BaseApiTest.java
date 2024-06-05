package epam.api;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.services.listener.TestListener;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
@ExtendWith(ReportPortalExtension.class)
public class BaseApiTest {
}
