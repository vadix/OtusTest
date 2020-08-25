import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class WebDriverFactory {

    public enum DriverType {
        CHROME,
        FIREFOX
    }
    public static WebDriver create(DriverType type) {
        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();

                return new ChromeDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();


                return new FirefoxDriver();
            default:
                throw new RuntimeException("Unsupported driver");
        }

    }
}