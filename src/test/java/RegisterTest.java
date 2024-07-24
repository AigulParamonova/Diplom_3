import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import pageobject.RegisterPage;
import serializer.User;
import serializer.UserAuthGenerator;
import serializer.UserGenerator;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pageobject.AuthorizationPage.*;
import static pageobject.RegisterPage.*;
import static pageobject.MainPage.*;
import static pageobject.ProfilePage.*;

public class RegisterTest {
    private WebDriver driver;
    private User user;

    @Before
    public void setUp() {
        driver = BrowserSettings.browserSettings();
        user = UserGenerator.getRandomUser();
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registerTest() throws InterruptedException {
        RegisterPage page = new RegisterPage(driver);
        page.openPage(REGISTRATION_PAGE_URL);
        page.findElementAndInputData(FIELD_NAME, user.getName());
        page.findElementAndInputData(FIELD_EMAIL, user.getEmail());
        page.findElementAndInputData(FIELD_PASSWORD, user.getPassword());
        page.findAndClickElement(REGISTRATION_BUTTON);
        assertTrue(page.isElementDisplayed(page.findElement(LOGIN_HEADER)));

        //Проверка авторизации зарегистированного пользователя
        page.findElementAndInputData(FIELD_EMAIL_LOGIN, user.getEmail());
        page.findElementAndInputData(FIELD_PASSWORD_LOGIN, user.getPassword());
        page.findAndClickElement(LOGIN_BUTTON);
        //Проверка отображения Главная-Конструктор
        assertTrue(page.isElementDisplayed(page.findElement(SET_BURGER_HEADER)));
        //Проверка имени и email
        page.findAndClickElement(PROFILE_LINK);
        assertEquals(user.getName(), page.findElement(FIELD_NAME_PROFILE).getAttribute("value"));
        assertEquals(user.getEmail().toLowerCase(), page.findElement(FIELD_EMAIL_PROFILE).getAttribute("value"));
        page.findAndClickElement(LOGOUT_BUTTON);
        assertTrue(page.isElementDisplayed(page.findElement(LOGIN_HEADER)));
    }

    @After
    public void tearDown() {
        driver.quit();
        UserSteps userSteps = new UserSteps();
        ValidatableResponse loginResponse = userSteps.loginUser(UserAuthGenerator.from(user));
        userSteps.deleteUser(loginResponse.extract().path("accessToken"));
    }
}
