package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.vaadin.addonhelpers.components.VaadinMenuBar;

public class MenuDemoPage {
    private WebDriver driver;
    @FindBy(id = "menubar")
    private WebElement menubar;
    @FindBy(id = "selection")
    private WebElement selection;

    public MenuDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSelectionText() {
        return selection.getText();
    }

    public MenuDemoPage clickNavigation(String caption) {
        VaadinMenuBar vaadinMenuBar = new VaadinMenuBar(menubar, driver);
        vaadinMenuBar.click(caption);
        return this;
    }

    public MenuDemoPage openSub(String caption) {
        VaadinMenuBar vaadinMenuBar = new VaadinMenuBar(menubar, driver);
        vaadinMenuBar.mouseOver(caption);
        return this;

    }
}
