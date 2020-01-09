import io.trueautomation.client.driver.TrueAutomationDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.trueautomation.client.TrueAutomationHelper.ta;

public class TestWithInitialLocator {

    private final static long DEFAULT_SLEEP_TIMEOUT = 20;

    private WebDriver driver;

    private By generalDonateBtn = By.xpath(ta("masterPass:generalDonateBtn", "//*[@id='generalDonateBtn']"));
    private By USD100 = By.xpath(ta("masterPass:100USD", "//*[text()='$100']"));
    private By firstName = By.xpath(ta("masterPass:firstName", "//input[@id='receiptDetailsForm--firstName']"));
    private By lastName = By.xpath(ta("masterPass:lastName", "//input[@id='receiptDetailsForm--lastName']"));
    private By email = By.xpath(ta("masterPass:email", "//input[@id='receiptDetailsForm--email']"));
    private By clickToPayBtn = By.xpath(ta("masterPass:clickPay", "//*[text()='Click to pay']"));
    private By masterPassBtn = By.xpath(ta("masterPass:masterPassBtn", "//*[@id='masterpass-button']"));
    private By cardNumber = By.id(ta("checkout:cardNumber", "card-number"));

    @BeforeTest
    public void beforeTest() {
        driver = new TrueAutomationDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void exampleTest() {
        driver.get("https://us.movember.com/donate");
        Assert.assertTrue(isElementVisible(generalDonateBtn), "Movember page is not loaded");

        driver.findElement(generalDonateBtn).click();
        Assert.assertTrue(isElementVisible(USD100), "Movember details page is not loaded");

        driver.findElement(USD100).click();
        driver.findElement(firstName).sendKeys("Testingname");
        driver.findElement(lastName).sendKeys("Testinglastname");
        driver.findElement(email).sendKeys("testingMail@gmail.com");
        driver.findElement(clickToPayBtn).click();

        driver.findElement(masterPassBtn).click();

        switchCheckoutWindow();

        Assert.assertTrue(isElementVisible(cardNumber), "Checkout window is not loaded");

    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    private boolean isElementVisible(By selector) {
        return waitForCondition(ExpectedConditions.visibilityOfElementLocated(selector), DEFAULT_SLEEP_TIMEOUT);
    }

    private boolean waitForCondition(ExpectedCondition<WebElement> webElementExpectedCondition, long timeout) {
        try {
            new WebDriverWait(driver, timeout).until(webElementExpectedCondition);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    private void switchCheckoutWindow(){
        String parentWindow = driver.getWindowHandle();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        driver.switchTo().window(driver
                        .getWindowHandles()
                        .stream()
                        .filter(window -> !window.equalsIgnoreCase(parentWindow))
                        .findFirst()
                        .orElseThrow(() -> new NoSuchWindowException("No new window found")));
    }
}
