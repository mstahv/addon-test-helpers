package org.vaadin.addonhelpers.automated;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.net.*;

import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import org.vaadin.addonhelpers.manual.demo.MenuDemo;

public class TestMenuDemo extends AbstractWebDriverCase {
    MenuDemoPage page;

    @Before
    public void setup() throws MalformedURLException {
        startBrowser();
        driver.navigate().to(
                new URL("http://localhost:5678/") + MenuDemo.class.getName());

        page = PageFactory.initElements(driver, MenuDemoPage.class);
        waitForLoading();
    }

    @Test
    public void testClickTop() {
        page.clickNavigation("Services");

        assertThat(page.getSelectionText(), is("Ordered a Services from menu."));
    }

    @Test
    public void testClickSubmenu() {
        page.openSub("Services").clickNavigation("Car Service");

        assertThat(page.getSelectionText(),
                is("Ordered a Car Service from menu."));
    }
    
    @Test
    public void testClickSubSubmenu() {
        page.openSub("Beverages").openSub("Hot").clickNavigation("Tea");

        assertThat(page.getSelectionText(),
                is("Ordered a Tea from menu."));
    }
}
