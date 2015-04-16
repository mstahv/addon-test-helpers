package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vaadin.addonhelpers.components.VaadinComboBox;

public class ComboBoxDemoPage {
    private WebDriver driver;

    @FindBy(id = "selection")
    private WebElement label;
    @FindBy(id = "combobox-1")
    private WebElement comboBox;
    @FindBy(id = "combobox-2")
    private WebElement comboBox2;

    private VaadinComboBox vaadinComboBox = null;
    private VaadinComboBox vaadinComboBox2 = null;

    public ComboBoxDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLabelText() {
        return label.getText();
    }

    public String getComboBoxValue() {
        return getVaadinComboBox().getValue();
    }

    public String getComboBoxValue2() {
        return getVaadinComboBox2().getValue();
    }

    public void enterText(String text) {
        getVaadinComboBox().getInput().clear();
        getVaadinComboBox().typeText(text);
        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());
        getVaadinComboBox().getInput().sendKeys(Keys.ENTER);
    }

    public void selectItem(String item) {
        getVaadinComboBox().selectItemFromFilter(item);
    }

    public void selectItem2(String item) {
        getVaadinComboBox2().selectItemFromFilter(item);
    }

    public void selectItem(int index) {
        getVaadinComboBox().selectItemFromFilter(index);
    }

    private VaadinComboBox getVaadinComboBox() {
        if (vaadinComboBox == null) {
            vaadinComboBox = new VaadinComboBox(comboBox);
        }
        return vaadinComboBox;
    }

    private VaadinComboBox getVaadinComboBox2() {
        if (vaadinComboBox2 == null) {
            vaadinComboBox2 = new VaadinComboBox(comboBox2);
        }
        return vaadinComboBox2;
    }

    public void enterText2(String text) {
        getVaadinComboBox2().getInput().clear();
        getVaadinComboBox2().typeText(text);
        new WebDriverWait(driver, 30).until(VaadinConditions
                .ajaxCallsCompleted());
        getVaadinComboBox2().getInput().sendKeys(Keys.ENTER);
        
    }
}
