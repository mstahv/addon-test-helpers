package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.*;
import java.util.List;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.vaadin.addonhelpers.components.VaadinTabSheet;
import org.vaadin.addonhelpers.manual.demo.TabSheetDemo;

public class TestTabSheetDemo extends AbstractWebDriverCase {

    private TabSheetDemoPage page;

    @Before
    public void setUp() throws MalformedURLException {
        startBrowser();
        driver.navigate().to(
                new URL("http://localhost:5678/"
                        + TabSheetDemo.class.getCanonicalName()));
        page = new TabSheetDemoPage();
        PageFactory.initElements(driver, page);
        waitForLoading();
    }

    @Test
    public void testTabSheet_getCaptions() {
        VaadinTabSheet tabSheet = page.getTabSheet();
        List<String> captions = tabSheet.getCaptions();
        assertThat(captions.get(0), is("Tab 1 Caption"));
        assertThat(captions.get(1), is("Tab 2 Caption"));
    }

    @Test
    public void testTabSheet_clickCaption() {
        VaadinTabSheet tabSheet = page.getTabSheet();
        assertThat(tabSheet.getSelectedCaption(), is("Tab 1 Caption"));

        tabSheet.click(1);
        assertThat(tabSheet.getSelectedCaption(), is("Tab 2 Caption"));
    }

    @Test
    public void testTabSheet_getContent() {
        VaadinTabSheet tabSheet = page.getTabSheet();
        WebElement content = tabSheet.getContent().findElement(
                By.className("tab-content-label"));
        assertThat(content.getText(), is("Tab1"));

        tabSheet.click(1);
        content = tabSheet.getContent().findElement(
                By.className("tab-content-label"));
        assertThat(content.getText(), is("Tab2"));
    }
}
