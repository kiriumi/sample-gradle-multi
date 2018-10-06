package sample.gradle.multi.web.logic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class LoginBeanTest {

    @Before
    public void setUp() throws Exception {

        String userDir = System.getProperty("user.dir");

        File webdriver = new File(new File(userDir).getParent(),
                "master/resources/web-driver/chromedriver.exe");

        Configuration.browser = WebDriverRunner.CHROME;
        System.setProperty("webdriver.chrome.driver", webdriver.getPath());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test() {

        Selenide.open("http://localhost:8080/SampleWebApp/login.xhtml");

        Selenide.$("#id").setValue("id");
        Selenide.$("#password").setValue("password");

        Selenide.$("#button").click();

        String success = Selenide.$("#success").getValue();

        assertThat(success, is("成功"));
    }
}
