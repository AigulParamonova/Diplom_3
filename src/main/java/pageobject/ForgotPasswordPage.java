package pageobject;

import org.openqa.selenium.By;

import static pageobject.Page.BASE_URL;

public class ForgotPasswordPage {
    public final static String FORGOT_PASSWORD_PAGE_URL = BASE_URL + "/forgot-password";
    //Ссылка "Войти"
    public final static By SIGN_IN_LINK = By.xpath("//a[@href='/login']");
}
