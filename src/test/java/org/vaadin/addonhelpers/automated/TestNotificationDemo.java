package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vaadin.addonhelpers.components.VaadinNotification;
import org.vaadin.addonhelpers.manual.demo.NotificationDemo;


public class TestNotificationDemo extends AbstractWebDriverCase {
    private NotificationDemoPage page;

    @Before
    public void setUp() {
        startBrowser();
        driver.navigate().to(
                "http://localhost:5678/" + NotificationDemo.class.getName());

        page = PageFactory.initElements(driver, NotificationDemoPage.class);
        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());
        List<VaadinNotification> warnings = VaadinNotification.get(
                VaadinNotification.Type.WARNING_MESSAGE, driver);
        warnings.get(0).click(); // Message when opening demo page
        waitForLoading();
    }

    @Test
    public void test_warn() {
        page.clickWarnButton();
        waitForLoading();
        List<VaadinNotification> warnings = VaadinNotification.get(
                VaadinNotification.Type.WARNING_MESSAGE, driver);
        assertThat(warnings.get(0).getCaption(), is("Warning"));
        assertThat(warnings.get(0).getDescription(), is("Warning description"));

        warnings.get(0).click();
        waitForLoading();
        assertThat(
                VaadinNotification.get(VaadinNotification.Type.WARNING_MESSAGE,
                        driver).size(), is(0));
    }

    @Test
    public void test_error() {
        page.clickErrorButton();
        waitForLoading();

        List<VaadinNotification> warnings = VaadinNotification.get(
                VaadinNotification.Type.ERROR_MESSAGE, driver);
        assertThat(warnings.get(0).getCaption(), is("Error"));
        assertThat(warnings.get(0).getDescription(), is("Error description"));

        warnings.get(0).click();
        waitForLoading();
        assertThat(
                VaadinNotification.get(VaadinNotification.Type.ERROR_MESSAGE,
                        driver).size(), is(0));
    }

    @Test
    public void test_tray() {
        page.clickTrayButton();
        
        waitForLoading();

        List<VaadinNotification> warnings = VaadinNotification.get(
                VaadinNotification.Type.TRAY_NOTIFICATION, driver);
        assertThat(warnings.get(0).getCaption(), is("Tray"));
        assertThat(warnings.get(0).getDescription(), is("Tray description"));

        warnings.get(0).click();
        waitForLoading();
        assertThat(
                VaadinNotification.get(
                        VaadinNotification.Type.TRAY_NOTIFICATION, driver)
                        .size(), is(0));
    }
}
