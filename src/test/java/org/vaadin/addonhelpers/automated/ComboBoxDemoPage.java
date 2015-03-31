package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.vaadin.addonhelpers.components.VaadinComboBox;

public class ComboBoxDemoPage {
    @FindBy(id = "selection")
    private WebElement label;
    @FindBy(id = "comboBox")
    private WebElement comboBox;

    private VaadinComboBox vaadinComboBox = null;

    public String getLabelText() {
        return label.getText();
    }

    public String getComboBoxValue()
    {
        return getVaadinComboBox().getValue();
        
    }
    public void enterText(String text) {
        getVaadinComboBox().getInput().clear();
        getVaadinComboBox().typeText(text);
        getVaadinComboBox().getInput().sendKeys(Keys.ENTER);
    }

    public void selectItem(String item) {
        getVaadinComboBox().selectItemFromFilter(item);
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
}
