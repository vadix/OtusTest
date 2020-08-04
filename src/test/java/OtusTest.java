import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import config.ServerConfig;

import static org.junit.Assert.assertTrue;

public class OtusTest {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(OtusTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void openPage() {
        String title = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        assertTrue(driver.getTitle().contains(title));
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }

}
}
