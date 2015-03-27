package org.vaadin.addonhelpers.automated;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.*;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Test for comparing tow different waiting strategies.
 */
public class TestSlowUIDemo extends AbstractWebDriverCase {
    private StopWatch watch;
    @Rule
    public TestName testName = new TestName();
    private SlowUIDemoPage page;

    @Before
    public void setUp() {
        watch = new StopWatch();
        watch.start();
    }

    @Override
    public void tearDown() {
        super.tearDown();
        watch.stop();
        System.out.println("The test: " + testName.getMethodName() + " took "
                + watch.getTime() + "ms.");
    }

    @Test
    public void test_NoWait() throws MalformedURLException {
        initPage();

        assertThat(page.getLabelText(), is(""));

        page.clickSlowButton();

        assertThat("There should not be a result yet.", page.getLabelText(),
                is(""));
    }

    @Test
    public void test_slowButton_WaitForLoading() throws MalformedURLException {

        initPage();

        assertThat(page.getLabelText(), is(""));

        page.clickSlowButton();

        waitForLoading();

        assertThat(page.getLabelText(), is("42"));
    }

    @Test
    public void test_slowButton_WaitVaadinCondition()
            throws MalformedURLException {
        initPage();

        assertThat(page.getLabelText(), is(""));

        page.clickSlowButton();

        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());

        assertThat(page.getLabelText(), is("42"));
    }

    @Test
    public void test_fastButton_WaitForLoading() throws MalformedURLException {

        initPage();

        assertThat(page.getLabelText(), is(""));

        page.clickFastButton();

        waitForLoading();

        assertThat(page.getLabelText(), is("42"));
    }

    @Test
    public void test_fastButton_WaitVaadinCondition()
            throws MalformedURLException {
        initPage();

        assertThat(page.getLabelText(), is(""));

        page.clickFastButton();

        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());

        assertThat(page.getLabelText(), is("42"));
    }

    protected void initPage() throws MalformedURLException {
        startBrowser();
        driver.navigate()
                .to(new URL(
                        "http://localhost:5678/org.vaadin.addonhelpers.manual.demo.SlowUIDemo"));
        page = new SlowUIDemoPage();
        PageFactory.initElements(driver, page);
    }
}
