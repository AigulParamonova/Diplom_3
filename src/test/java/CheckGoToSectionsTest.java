import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobject.MainPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static pageobject.MainPage.*;

@RunWith(Parameterized.class)
public class CheckGoToSectionsTest {
    private WebDriver driver;
    private final By locatorTypeComponents;
    private final By locatorLabelComponents;
    private final int count;

    public CheckGoToSectionsTest(By locatorTypeComponents, By locatorLabelComponents, int count){
        this.locatorTypeComponents = locatorTypeComponents;
        this.locatorLabelComponents = locatorLabelComponents;
        this.count = count;
    }

    @Parameterized.Parameters(name = "{index}: Вид компонента - {0}")
    public static Object[][] Data() {
        return new Object[][]{
                {BUNS, LABEL_BUNS, 1},
                {SAUCES, LABEL_SAUCES, 1},
                {INGREDIENTS, LABEL_INGREDIENTS, 1},
        };
    }

    @Before
    public void setUp() {
        driver = BrowserSettings.browserSettings();
    }

    @Test
    @DisplayName("Проверка, что работают переходы к разделам Булки, Соусы, Начинки")
    public void checkGoToSectionsTest() throws InterruptedException {
        MainPage page = new MainPage(driver);
        page.openPage(BASE_URL);
        if (locatorTypeComponents.equals(BUNS)) page.findAndClickElementWithWaiting(SAUCES);
        page.findAndClickElementWithWaiting(locatorTypeComponents);
        int LocatorTypeComponents = page.findElement(locatorTypeComponents).getLocation().y;
        for (int i = 1; i <= count - 1; i++) {
            List<WebElement> elementsAbove = new ArrayList<>(driver.findElements(By.xpath(String.format(MENU_ELEMENTS, i))));
            for (WebElement element:elementsAbove) {
                int yElement = element.getLocation().y;
                assertTrue(yElement < LocatorTypeComponents);
            }
        }
        int LocatorLabelComponents = page.findElement(locatorLabelComponents).getLocation().y;
        assertTrue(LocatorLabelComponents > LocatorTypeComponents);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
