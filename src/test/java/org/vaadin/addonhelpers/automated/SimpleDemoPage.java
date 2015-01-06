package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SimpleDemoPage {
    @FindBy(id = "label")
    private WebElement label;
    @FindBy(id = "toggleButton")
    private WebElement button;

    public String getLabelText() {
        return label.getText();
    }

    public void clickButton() {
        button.click();
    }
}
