import browser.Browser;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserSettings {
    private final static Browser CURRENT_BROWSER = Browser.YANDEX;

    public static WebDriver browserSettings(){
        WebDriver driver = null;
        switch (CURRENT_BROWSER) {
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "/Users/user/Downloads/WebDriver/bin/yandexdriver");
                driver = new ChromeDriver();
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }
}
