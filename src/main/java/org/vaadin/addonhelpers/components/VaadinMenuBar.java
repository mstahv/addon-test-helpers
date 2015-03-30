package org.vaadin.addonhelpers.components;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class VaadinMenuBar {

    private WebElement menuBar;
    private WebDriver driver;

    public VaadinMenuBar(WebElement menubar, WebDriver driver) {
        this.menuBar = menubar;
        this.driver = driver;
    }

    public void click(String caption) {
        menuBar.findElement(
                By.xpath("//span[@class=\"v-menubar-menuitem-caption\" and contains(text(), '"
                        + caption + "')]")).click();

    }

    public VaadinMenuBar mouseOver(String caption) {
        Actions action = new Actions(driver);
        WebElement we = menuBar
                .findElement(By
                        .xpath("//span[@class=\"v-menubar-menuitem-caption\" and contains(text(), '"
                                + caption + "')]"));
        action.moveToElement(we).build().perform();
        return this;
    }
}
