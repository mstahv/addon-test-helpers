package org.vaadin.addonhelpers.components;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

/**
 * Wrapper around a selenium WebElement for a {@link com.vaadin.ui.Notification}
 * .
 * 
 * <p>
 * Because vaadin places notifications outside the "regular" DOM in an overlay
 * there is a factory method to instantiate them.
 * 
 * @author Daniel Nordhoff-Vergien
 */
public class VaadinNotification {
    private final static String BASE_CLASS = "v-Notification";
    private final WebElement webElement;
    private final String caption;
    private final String description;

    public enum Type {
        HUMANIZED_MESSAGE, WARNING_MESSAGE, ERROR_MESSAGE, TRAY_NOTIFICATION
    }

    /**
     * Returns a list of notifications of the given type.
     * 
     * @param type
     *            the notification type
     * @param driver
     * @return A list of notifications.
     */
    public static List<VaadinNotification> get(Type type, WebDriver driver) {
        List<WebElement> notifications = driver.findElements(By
                .className(getClassName(type)));
        List<VaadinNotification> vaadinNotification = new ArrayList<VaadinNotification>();
        for (WebElement notification : notifications) {
            String notificationCaption = null;
            try {
                notificationCaption = notification
                        .findElement(By.tagName("h1")).getText();
            } catch (NoSuchElementException e) {
                // This happens if there is no caption
            }
            String notificationDescription = null;
            try {
                notificationDescription = notification.findElement(
                        By.tagName("p")).getText();
            } catch (NoSuchElementException e) {
                // This happens if there is no description
            }
            vaadinNotification.add(new VaadinNotification(notificationCaption,
                    notificationDescription, notification));
        }
        return vaadinNotification;

    }

    public VaadinNotification(String caption, String description,
            WebElement webElement) {
        super();
        this.caption = caption;
        this.description = description;
        this.webElement = webElement;
    }

    private static String getClassName(Type type) {
        switch (type) {
        case ERROR_MESSAGE:
            return BASE_CLASS + "-error";
        case WARNING_MESSAGE:
            return BASE_CLASS + "-warning";
        case HUMANIZED_MESSAGE:
            return BASE_CLASS + "-humanized";
        case TRAY_NOTIFICATION:
            return BASE_CLASS + "-tray";
        default:
            return BASE_CLASS;
        }
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public void click() {
        webElement.click();
    }
}
