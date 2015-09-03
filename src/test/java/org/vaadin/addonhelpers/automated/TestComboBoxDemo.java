package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.*;

import org.junit.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestComboBoxDemo extends AbstractWebDriverCase {

    private ComboBoxDemoPage comboBoxDemoPage;

    @Before
    public void initPage() throws MalformedURLException {
        startBrowser();
        driver.navigate().to(new URL(
                "http://localhost:5678/org.vaadin.addonhelpers.manual.demo.ComboBoxDemo"));
        waitForLoading();
        comboBoxDemoPage = PageFactory.initElements(driver,
                ComboBoxDemoPage.class);
        new WebDriverWait(driver, 30)
                .until(VaadinConditions.ajaxCallsCompleted());
    }

    @Test
    public void testComboBox() {
        assertThat(comboBoxDemoPage.getComboBoxValue(), is(""));
        assertThat(comboBoxDemoPage.getLabelText(), is(""));

        comboBoxDemoPage.selectItem(2);
        new WebDriverWait(driver, 30)
                .until(VaadinConditions.ajaxCallsCompleted());
        assertThat(comboBoxDemoPage.getComboBoxValue(), is("Value 1"));
        assertThat(comboBoxDemoPage.getLabelText(), is("Value 1"));

        comboBoxDemoPage.enterText("Value 20", true);
        new WebDriverWait(driver, 30)
                .until(VaadinConditions.ajaxCallsCompleted());
        assertThat(comboBoxDemoPage.getComboBoxValue(), is("Value 20"));
        assertThat(comboBoxDemoPage.getLabelText(), is("Value 20"));

        comboBoxDemoPage.enterText2("other Value 20");
        assertThat(comboBoxDemoPage.getComboBoxValue2(), is("other Value 20"));
    }

    @Test
    public void testComboBox_typeTextThenSelect() {
        comboBoxDemoPage.enterText("Value 9", false);
        comboBoxDemoPage.selectItem(1);

        new WebDriverWait(driver, 30)
                .until(VaadinConditions.ajaxCallsCompleted());
        assertThat(comboBoxDemoPage.getLabelText(), is("Value 90"));
        assertThat(comboBoxDemoPage.getComboBoxValue(), is("Value 90"));
    }
}
