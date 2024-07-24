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
import static pageobject.ForgotPasswordPage.*;
import static pageobject.AuthorizationPage.*;
import static pageobject.MainPage.*;
import static pageobject.RegisterPage.*;

@RunWith(Parameterized.class)
public class AuthorizationTest {
    private WebDriver driver;
    private UserSteps userSteps;
    private String accessToken;
    private User user;
    private final String url;
    private final By locator;

    public AuthorizationTest(String url, By locator){
        this.url = url;
        this.locator = locator;
    }

    @Parameterized.Parameters(name = "{index}: Страница - {0}, элемент - {1}")
    public static Object[][] Data() {
        return new Object[][]{
                {BASE_URL, SIGN_IN_ACCOUNT_BUTTON},
                {BASE_URL, PROFILE_LINK},
                {FORGOT_PASSWORD_PAGE_URL, SIGN_IN_LINK},
                {REGISTRATION_PAGE_URL, REGISTRATION_SIGN_IN_LINK}
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
    @DisplayName("Авторизация пользователя с главной страницы,c личного кабинета, " +
                 "страницы восстановления пароля, страницы регистрации")
    public void userLoginTest(){
        MainPage page = new MainPage(driver);
        page.openPage(url);
        page.findAndClickElement(locator);
        assertTrue(page.isElementDisplayed(page.findElement(LOGIN_HEADER)));

        page.findElementAndInputData(FIELD_EMAIL_LOGIN, user.getEmail());
        page.findElementAndInputData(FIELD_PASSWORD_LOGIN, user.getPassword());
        page.findAndClickElement(LOGIN_BUTTON);
        assertTrue(page.isElementDisplayed(page.findElement(SET_BURGER_HEADER)));
    }


    @After
    public void tearDown() {
        driver.quit();
        userSteps.deleteUser(accessToken);
    }
}
