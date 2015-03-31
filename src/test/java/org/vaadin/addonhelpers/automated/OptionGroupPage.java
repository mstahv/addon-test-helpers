package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.vaadin.addonhelpers.components.VaadinOptionGroup;

public class OptionGroupPage {
    @FindBy(id = "single-optiongroup")
    private WebElement singeOptionGroup;
    @FindBy(id = "multi-optiongroup")
    private WebElement multiOptionGroup;

    public VaadinOptionGroup getSingleOptionGroup() {
        return new VaadinOptionGroup(singeOptionGroup);
    }

    public VaadinOptionGroup getMultiOptionGroup() {
        return new VaadinOptionGroup(multiOptionGroup);
    }
}
