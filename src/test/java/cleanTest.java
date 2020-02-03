import io.trueautomation.client.driver.TrueAutomationDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class cleanTest {

    private final static long DEFAULT_SLEEP_TIMEOUT = 20;

    private WebDriver driver;

    private By generalDonateBtn;
    private By USD100;
    private By firstName;
    private By lastName;
    private By email;
    private By clickToPayBtn;
    private By masterPassBtn;
    private By cardNumber;

    @BeforeTest
    public void beforeTest() {
        driver = new TrueAutomationDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void exampleTest() throws InterruptedException {
        driver.get("https://us.movember.com/donate");

        // Assert: wait donate btn

        // Click donate btn
        // Assert: wait usd 100 btn


        // Click usd 100 btn
        // fill first name & last name & email
        // Click click to pay btn

        // Click master-card widget


        // switchCheckoutWindow();

        // Assert: wait card number field
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

    private void switchCheckoutWindow() {
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
