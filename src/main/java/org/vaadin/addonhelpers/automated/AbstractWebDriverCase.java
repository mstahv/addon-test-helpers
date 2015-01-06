package org.vaadin.addonhelpers.automated;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vaadin.addonhelpers.TServer;

/**
 * This abstract class can be used if one e.g. cannot afford TestBench license.
 *
 */
public class AbstractWebDriverCase {

    protected static final int TESTPORT = 5678;
    protected static final String BASEURL = "http://localhost:" + TESTPORT
            + "/";
    protected WebDriver driver;
    private static Server server;

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

    public AbstractWebDriverCase() {
        super();
    }

    @BeforeClass
    public static void startServer() {
        try {
            server = new TServer().startServer(TESTPORT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @After
    public void tearDown() {

    }

    protected void startBrowser() {
        if (driver != null) {
            driver.quit();
        }
        driver = new FirefoxDriver();
    }

    protected void clickNotification() {
        try {
            WebElement notification = driver.findElement(By
                    .className("v-Notification"));
            notification.click();
        } catch (NoSuchElementException e) {
            throw e;
        }

    }

    protected void waitForLoading() {
        sleep(1000);
        // driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                boolean stopWait = false;
                try {
                    WebElement findElement = d.findElement(By
                            .className("v-loading-indicator"));
                    String cssValueDisplay = findElement.getCssValue("display");
                    if (StringUtils.equals("none", cssValueDisplay)) {
                        stopWait = true;
                    }
                } catch (NoSuchElementException e) {
                    stopWait = true;
                }
                return stopWait;
            }
        });
    }

    protected void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class ScreenshotTestRule implements MethodRule {
        public Statement apply(final Statement statement,
                final FrameworkMethod frameworkMethod, final Object o) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        statement.evaluate();
                    } catch (Throwable t) {
                        captureScreenshot(frameworkMethod.getName());
                        // rethrow to allow the failure to be reported to JUnit
                        throw t;
                    } finally {
                        // Closing the webdriver
                        after();
                    }
                }

                public void after() {
                    // We can't close the webdriver in @After annotated method,
                    // because the method is called before this rule
                    if (driver != null) {
                        driver.quit();
                    }
                }

                public void captureScreenshot(String fileName) {
                    try {// Insure directory is there
                        new File("target/surefire-reports/screenshots/")
                                .mkdirs();
                        FileOutputStream out = new FileOutputStream(
                                "target/surefire-reports/screenshots/"
                                        + fileName + ".png");
                        out.write(((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES));
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            };
        }
    }
}