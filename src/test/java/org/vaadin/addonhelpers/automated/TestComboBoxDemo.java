package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestComboBoxDemo extends AbstractWebDriverCase {
    @Test
    public void testComboBox() throws MalformedURLException {
        startBrowser();
        driver.navigate()
                .to(new URL(
                        "http://localhost:5678/org.vaadin.addonhelpers.manual.demo.ComboBoxDemo"));
        waitForLoading();
        ComboBoxDemoPage comboBoxDemoPage = PageFactory.initElements(driver,
                ComboBoxDemoPage.class);
        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());

        assertThat(comboBoxDemoPage.getComboBoxValue(), is(""));
        assertThat(comboBoxDemoPage.getLabelText(), is(""));

        comboBoxDemoPage.selectItem(2);
        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());
        assertThat(comboBoxDemoPage.getComboBoxValue(), is("Value 1"));
        assertThat(comboBoxDemoPage.getLabelText(), is("Value 1"));

        comboBoxDemoPage.enterText("Value 20");
        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());
        assertThat(comboBoxDemoPage.getComboBoxValue(), is("Value 20"));
        assertThat(comboBoxDemoPage.getLabelText(), is("Value 20"));

        comboBoxDemoPage.enterText2("other Value 20");
        assertThat(comboBoxDemoPage.getComboBoxValue2(), is("other Value 20"));
    }
}
