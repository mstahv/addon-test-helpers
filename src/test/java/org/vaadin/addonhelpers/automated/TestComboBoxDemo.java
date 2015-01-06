package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class TestComboBoxDemo extends AbstractWebDriverCase {
    @Test
    public void testComboBox() throws MalformedURLException {
        startBrowser();
        driver.navigate()
                .to(new URL(
                        "http://localhost:5678/org.vaadin.addonhelpers.manual.demo.ComboBoxDemo"));
        waitForLoading();
        ComboBoxDemoPage comboBoxDemoPage = new ComboBoxDemoPage();
        PageFactory.initElements(driver, comboBoxDemoPage);

        assertThat(comboBoxDemoPage.getLabelText(), is(""));

        comboBoxDemoPage.selectItem(2);
        assertThat(comboBoxDemoPage.getLabelText(), is("Value 1"));
        
        comboBoxDemoPage.enterText("Value 20");
        assertThat(comboBoxDemoPage.getLabelText(), is("Value 20"));
    }
}
