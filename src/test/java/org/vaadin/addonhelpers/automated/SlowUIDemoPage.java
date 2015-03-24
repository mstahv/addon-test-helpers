package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SlowUIDemoPage {
    @FindBy(id = "result")
    private WebElement label;
    @FindBy(id = "slowComputeButton")
    private WebElement slowButton;
    @FindBy(id = "fastComputeButton")
    private WebElement fastButton;

    public String getLabelText() {
        return label.getText();
    }

    public void clickSlowButton() {
        slowButton.click();
    }

    public void clickFastButton() {
        fastButton.click();
    }
}
