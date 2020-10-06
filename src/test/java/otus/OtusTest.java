package otus;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import otus.config.ServerConfig;

import static org.junit.Assert.assertTrue;

public class OtusTest {
    private final Logger logger = LogManager.getLogger(OtusTest.class);
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private WebDriver driver;

    @Before
    public void setup() {

        String browserParam = cfg.browser();
        WebDriverFactory.DriverType driverType = WebDriverFactory.DriverType.valueOf(browserParam.toUpperCase());

        driver = WebDriverFactory.create(driverType);
        logger.info("Driver ready");
    }

    @Test
    public void openPage() {
        String title = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

        driver.get(cfg.url());
        logger.info("Otus page is opened");
        assertTrue(driver.getTitle().contains(title));
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
