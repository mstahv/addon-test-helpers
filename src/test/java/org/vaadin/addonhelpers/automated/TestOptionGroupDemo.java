package org.vaadin.addonhelpers.automated;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vaadin.addonhelpers.components.VaadinOptionGroup;
import org.vaadin.addonhelpers.manual.demo.OptionGroupDemo;

public class TestOptionGroupDemo extends AbstractWebDriverCase {
    private OptionGroupPage page;

    @Before
    public void setUp() {
        startBrowser();
        driver.navigate().to(
                "http://localhost:5678/" + OptionGroupDemo.class.getName());

        page = PageFactory.initElements(driver, OptionGroupPage.class);
        new WebDriverWait(driver, 30).until(VaadinConditions::ajaxCallsCompleted);
    }

    @Test
    @Ignore("God dammit, this is broken in Vaadin again!")
    public void testGetOptions() {
        assertThat(page.getSingleOptionGroup().getOptions(), is(Arrays.asList(
                new VaadinOptionGroup.Option(false, true, "Single"),
                new VaadinOptionGroup.Option(false, false, "Sola"),
                new VaadinOptionGroup.Option(false, true, "Yksi"))));

        assertThat(page.getMultiOptionGroup().getOptions(), is(Arrays.asList(
                new VaadinOptionGroup.Option(false, true, "Many"),
                new VaadinOptionGroup.Option(false, true, "Muchos"),
                new VaadinOptionGroup.Option(false, false, "Monta"))));
    }

    @Test
    public void testSelectOptions() {
        waitForLoading();
        VaadinOptionGroup singleOptionGroup = page.getSingleOptionGroup();

        singleOptionGroup.click(0);
        waitForLoading();
        assertThat(singleOptionGroup.getOptions(), is(Arrays.asList(
                new VaadinOptionGroup.Option(true, true, "Single"),
                new VaadinOptionGroup.Option(false, false, "Sola"),
                new VaadinOptionGroup.Option(false, true, "Yksi"))));

        singleOptionGroup.click(1); // nothing should change since its disabled
        assertThat(singleOptionGroup.getOptions(), is(Arrays.asList(
                new VaadinOptionGroup.Option(true, true, "Single"),
                new VaadinOptionGroup.Option(false, false, "Sola"),
                new VaadinOptionGroup.Option(false, true, "Yksi"))));        
    }
}
