package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.vaadin.addonhelpers.manual.demo.MenuDemo;

import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreFirefox;
import com.github.webdriverextensions.junitrunner.annotations.PhantomJS;
import com.github.webdriverextensions.vaadin.VaadinBot;

@RunWith(WebDriverRunner.class)
@PhantomJS
@Firefox
public class TestMenuDemo extends AbstractWebDriverCase {
    MenuDemoPage page;

    @Before
    public void setup() throws MalformedURLException {
        startBrowser(WebDriverExtensionsContext.getDriver());
        driver.navigate().to(
                new URL("http://localhost:5678/") + MenuDemo.class.getName());
        VaadinBot.waitForVaadin();
        page = PageFactory.initElements(driver, MenuDemoPage.class);
        clickNotification();
    }

    @Test
    public void testClickTop() {
        page.clickNavigation("Services");

        assertThat(page.getSelectionText(), is("Ordered a Services from menu."));
    }

    @Test
    @IgnoreFirefox
    public void testClickSubmenu() {
        page.openSub("Services").clickNavigation("Car Service");

        assertThat(page.getSelectionText(),
                is("Ordered a Car Service from menu."));
    }
    
    @Test
    @IgnoreFirefox
    public void testClickSubSubmenu() {
        page.openSub("Beverages").openSub("Hot").clickNavigation("Tea");

        assertThat(page.getSelectionText(),
                is("Ordered a Tea from menu."));
    }
}
