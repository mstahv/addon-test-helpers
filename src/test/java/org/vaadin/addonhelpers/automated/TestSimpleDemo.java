package org.vaadin.addonhelpers.automated;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class TestSimpleDemo extends AbstractWebDriverCase {

    @Test
    public void testClick() throws MalformedURLException {
        startBrowser();
        driver.navigate()
                .to(new URL(
                        "http://localhost:5678/org.vaadin.addonhelpers.manual.demo.SimpleDemo"));
        waitForLoading();
        SimpleDemoPage simpleDemoPage = new SimpleDemoPage();
        PageFactory.initElements(driver, simpleDemoPage);

        assertThat(simpleDemoPage.getLabelText(), is("OFF"));

        simpleDemoPage.clickButton();

        assertThat(simpleDemoPage.getLabelText(), is("ON"));
    }

    @Test
    @Ignore("Demo for a failing test, which is creating a screenshot.")
    public void testClick_fails() throws MalformedURLException {
        startBrowser();
        driver.navigate()
                .to(new URL(
                        "http://localhost:5678/org.vaadin.addonhelpers.manual.demo.SimpleDemo"));
        waitForLoading();
        SimpleDemoPage simpleDemoPage = new SimpleDemoPage();
        PageFactory.initElements(driver, simpleDemoPage);

        assertThat(simpleDemoPage.getLabelText(), is("ON"));
    }
}
