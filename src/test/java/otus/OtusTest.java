package otus;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otus.config.ServerConfig;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class OtusTest {
    private final Logger logger = LogManager.getLogger(OtusTest.class);
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void setup() {

        String browserParam = cfg.browser();
        WebDriverFactory.DriverType driverType = WebDriverFactory.DriverType.valueOf(browserParam.toUpperCase());

        driver = WebDriverFactory.create(driverType);
        webDriverWait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Драйвер готов");
    }

    @Test
    public void yandexMarketTest() {

        driver.get(cfg.url());
        driver.findElement(By.xpath("//span[text()='Электроника']")).click();
        driver.findElement(By.xpath("//a[text()='Смартфоны']")).click(); //Открыть в Chrome сайт Яндекс.Маркет - раздел "Мобильные телефоны"
        driver.findElement(By.xpath("//button[text()='Показать всё']")).click();
        driver.findElement(By.id("7893318-suggester")).sendKeys("xiaomi");
        driver.findElement(By.xpath("//span[text()='Xiaomi']")).click();
        driver.findElement(By.id("7893318-suggester")).clear();
        driver.findElement(By.id("7893318-suggester")).sendKeys("huawei");
        driver.findElement(By.xpath("//span[text()='HUAWEI']")).click();//Отфильтровать список товаров: huawei и Xiaomi
        driver.findElement(By.xpath("//button[text()='по цене']")).click();// Отсортировать список товаров по цене (от меньшей к большей)
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div._2LvbieS_AO._1oZmP3Lbj2")));
        logger.info("ожидаем загрузку списка телефонов");
        driver.findElement(By.xpath("//a[contains(@title,'Xiaomi')]/ancestor::article//div[contains(@aria-label,'сравнению')]")).click();//Добавить первый в списке Xiaomi
        logger.info("Xiaomi добавлен");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Товар Смартфон Xiaomi')]")));//Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        logger.info("Плашка отобразилась");
        driver.findElement(By.xpath("//a[contains(@title,'HUAWEI')]/ancestor::article//div[contains(@aria-label,'сравнению')]")).click();//Добавить первый в списке HUAWEI
        logger.info("Huawei добавлен");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Товар Смартфон HUAWEI')]"))); //Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        logger.info("Плашка отобразилась");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Всего в списке 2 товара из категории Мобильные телефоны')]"))); //Проверить, что в списке товаров 2 позиции
        driver.findElement(By.xpath("//span[text()='Сравнить']")).click();
        driver.findElement(By.xpath("//button[text()='Все характеристики']")).click(); //Нажать на опцию "все характеристики"
        logger.info("опция 'все характеристики' нажата");
        driver.findElement(By.xpath("//div[text()='Версия ОС на начало продаж']")); //Проверить, что в списке характеристик появилась позиция "Операционная система"
        driver.findElement(By.xpath("//button[text()='Различающиеся характеристики']")).click(); //Нажать на опцию "различающиеся характеристики"
        logger.info("опция 'различающиеся характеристики' нажата");
        assertEquals(0, driver.findElements(By.xpath("//div[text()='Версия ОС на начало продаж']")).size()); //Проверить, что позиция "Операционная система" не отображается в списке характеристик
        logger.info("позиция \"Операционная система\" не отображается в списке характеристик");

    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
