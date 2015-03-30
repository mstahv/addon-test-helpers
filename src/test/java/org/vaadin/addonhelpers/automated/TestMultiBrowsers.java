package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class TestMultiBrowsers extends AbstractWebDriverCase {
    @Parameters
    public static List<Object[]> browsers() throws MalformedURLException {
        List<Object[]> browsers = new ArrayList<Object[]>();
        // Needs installed chromedriver
        // browsers.add(new Object[] { getChromeDriver("/usr/lib/chromium-browser/chromedriver") });
        browsers.add(new Object[] { new FirefoxDriver() });
        return browsers;
    }

    protected static ChromeDriver getChromeDriver(String pathToChromeDriver) {
        if (StringUtils.isNoneBlank(pathToChromeDriver)) {
            System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        }
        ChromeDriver chromeDriver = new ChromeDriver();
        return chromeDriver;
    }

    public TestMultiBrowsers(WebDriver driver) {
        startBrowser(driver);
    }

    @Test
    public void testClick() throws MalformedURLException {
        driver.navigate()
                .to(new URL(
                        "http://localhost:5678/org.vaadin.addonhelpers.manual.demo.SimpleDemo"));
        waitForLoading();
        SimpleDemoPage simpleDemoPage = new SimpleDemoPage();
        PageFactory.initElements(driver, simpleDemoPage);

        assertThat(simpleDemoPage.getLabelText(), is("OFF"));

        simpleDemoPage.clickButton();

        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());

        assertThat(simpleDemoPage.getLabelText(), is("ON"));
    }
}
