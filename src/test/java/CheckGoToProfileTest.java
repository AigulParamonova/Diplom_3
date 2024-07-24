import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import serializer.User;
import serializer.UserGenerator;
import steps.UserSteps;

import static org.junit.Assert.assertTrue;
import static pageobject.AuthorizationPage.*;
import static pageobject.FeedPage.FEED_HEADER;
import static pageobject.MainPage.*;

@RunWith(Parameterized.class)
public class CheckGoToProfileTest {
    private WebDriver driver;
    private UserSteps userSteps;
    private String accessToken;
    private User user;
    private final By locator_menu;
    private final By locator_expected;

    public CheckGoToProfileTest(By locator_menu, By locator_expected){
        this.locator_menu = locator_menu;
        this.locator_expected = locator_expected;
    }

    @Parameterized.Parameters(name = "{index}: Пункт меню - {0}, ожидаемый элемент на странице - {1}")
    public static Object[][] Data() {
        return new Object[][]{
                {CONSTRUCTOR_LINK, SET_BURGER_HEADER},
                {FEED_LINK, FEED_HEADER},
                {LOGO_LINK, SET_BURGER_HEADER},
        };
    }

    @Before
    public void setUp() {
        driver = BrowserSettings.browserSettings();
        user = UserGenerator.getRandomUser();
        userSteps = new UserSteps();
        ValidatableResponse createResponse = userSteps.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

    }

    @Test
    @DisplayName("Переход в Личный кабинет, Конструктор и логотип Stellar Burgers")
    public void checkGoToProfileTest() throws InterruptedException {
        MainPage page = new MainPage(driver);
        page.openPage(LOGIN_PAGE_URL);
        page.findElementAndInputData(FIELD_EMAIL_LOGIN, user.getEmail());
        page.findElementAndInputData(FIELD_PASSWORD_LOGIN, user.getPassword());
        page.findAndClickElementWithWaiting(LOGIN_BUTTON);
        page.findAndClickElement(PROFILE_LINK);
        //Перейти в пункт меню
        page.findAndClickElementWithWaiting(locator_menu);
        assertTrue(page.isElementDisplayed(page.findElement(locator_expected)));
    }

    @After
    public void tearDown() {
        driver.quit();
        userSteps.deleteUser(accessToken);
    }
}
