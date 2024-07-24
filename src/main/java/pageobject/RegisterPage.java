package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static pageobject.Page.BASE_URL;

public class RegisterPage extends Page {
    public final static String REGISTRATION_PAGE_URL = BASE_URL + "/register";
    //Поле "Имя"
    public final static By FIELD_NAME = By.xpath("//label[text()='Имя']//following::input[1]");
    //Поле "Email"
    public final static By FIELD_EMAIL = By.xpath("//label[text()='Email']//following::input[1]");
    //Поле "Пароль"
    public final static By FIELD_PASSWORD = By.xpath("//input[@name='Пароль']");
    //Кнопка "Зарегистрироваться"
    public final static By REGISTRATION_BUTTON = By.xpath("//button[@class=\"button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa\"]");
    //Надпись "Некорректный пароль"
    public final static By INCORRECT_PASSWORD_LABEL = By.xpath("//p[@class=\"input__error text_type_main-default\" and text()=\"Некорректный пароль\"]");
    //Ссылка "Войти"
    public final static By REGISTRATION_SIGN_IN_LINK = By.xpath("//a[@href='/login']");
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
}
