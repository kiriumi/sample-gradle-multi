package logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginBeanTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                "../master/config/selenium/chromedriver.exe");

        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        driver.get("http://localhost:8080/SampleWebApp/login.xhtml");
        driver.quit();
    }

}
