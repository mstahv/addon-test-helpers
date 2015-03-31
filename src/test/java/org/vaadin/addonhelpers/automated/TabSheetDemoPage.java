package org.vaadin.addonhelpers.automated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.vaadin.addonhelpers.components.VaadinTabSheet;

public class TabSheetDemoPage {
    @FindBy(id = "tabsheet")
    private WebElement tabSheet;

    public VaadinTabSheet getTabSheet() {
        return new VaadinTabSheet(tabSheet);
    }

}
