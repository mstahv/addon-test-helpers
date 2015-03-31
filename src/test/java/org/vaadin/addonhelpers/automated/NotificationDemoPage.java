package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationDemoPage {
    @FindBy(id = "warn-button")
    private WebElement warnButton;
    @FindBy(id = "tray-button")
    private WebElement trayButton;
    @FindBy(id = "humanized-button")
    private WebElement humanizedButton;
    @FindBy(id = "error-button")
    private WebElement errorButton;

    public void clickErrorButton() {
        errorButton.click();
    }

    public void clickWarnButton() {
        warnButton.click();
    }

    public void clickTrayButton() {
        trayButton.click();
    }

    public void clickHumanizedButton() {
        humanizedButton.click();
    }
}
