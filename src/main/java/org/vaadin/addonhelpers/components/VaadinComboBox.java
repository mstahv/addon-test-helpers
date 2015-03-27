package org.vaadin.addonhelpers.components;

import java.util.List;

import org.openqa.selenium.*;

/**
 * Wrapper around a selenium WebElement for a vaadin combox.
 * 
 * @see <a href=
 *      "http://www.testingminded.com/2014/08/selecting-item-from-vaadin-dropdown.html"
 *      >Blog with the original idea</a>
 * @author Daniel Nordhoff-Vergien
 */
public class VaadinComboBox {
    private WebElement webElement;

    public VaadinComboBox(WebElement webElement) {
        super();
        this.webElement = webElement;
    }

    public void selectItemFromFilter(String searchItem) {
        List<WebElement> menuItems = getMenuItemsFromFilter();

        WebElement menuItemToSelect = null;
        for (WebElement menuItem : menuItems) {
            if (menuItem.getText().contains(searchItem)) {
                menuItemToSelect = menuItem;
                break;
            }
        }

        if (menuItemToSelect == null) {
            throw new IllegalArgumentException(
                    "Couldn't find menu item with text: " + searchItem);
        }
        menuItemToSelect.click();
    }

    public void typeText(String text) {
        WebElement input = getInput();
        input.sendKeys(text);
    }

    public WebElement getInput() {
        WebElement input = webElement.findElement(By
                .className("v-filterselect-input"));
        return input;
    }

    public void selectItemFromFilter(Integer index) {
        List<WebElement> menuItems = getMenuItemsFromFilter();

        try {
            WebElement menuItem = menuItems.get(index);
            menuItem.click();

        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    "Couldn't find menu item with index: " + index);
        }

    }

    private List<WebElement> getMenuItemsFromFilter() {
        By suggestMenuDropdownButtonLocator = By
                .xpath("//div[contains(@class, 'v-filterselect-button')]");
        By suggestMenuLocator = By
                .xpath("//div[contains(@class, 'v-filterselect-suggestmenu')]");
        By menuItemLocator = By.xpath("//td[contains(@class, 'gwt-MenuItem')]");

        WebElement suggestMenuDropDown = webElement
                .findElement(suggestMenuDropdownButtonLocator);

        suggestMenuDropDown.click();

        return webElement.findElement(suggestMenuLocator).findElements(
                menuItemLocator);
    }
}