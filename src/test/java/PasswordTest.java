import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;
import static pageobject.RegisterPage.*;

public class PasswordTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        driver = BrowserSettings.browserSettings();
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного парооля")
    public void passwordTest(){
        RegisterPage page = new RegisterPage(driver);
        page.openPage(REGISTRATION_PAGE_URL);
        page.findElementAndInputData(FIELD_PASSWORD,"12345");
        page.findAndClickElement(REGISTRATION_BUTTON);
        assertTrue(page.isElementDisplayed(page.findElement(INCORRECT_PASSWORD_LABEL)));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
