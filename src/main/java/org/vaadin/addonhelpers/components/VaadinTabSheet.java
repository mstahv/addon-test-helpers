package org.vaadin.addonhelpers.components;

import java.util.*;

import org.openqa.selenium.*;

/**
 * Wrapper around a {@link org.openqa.selenium.WebElement} representing a
 * {@link com.vaadin.ui.TabSheet}.
 * 
 * @author Daniel Nordhoff-Vergien
 *
 */
public class VaadinTabSheet {
    private WebElement tabSheet;

    public VaadinTabSheet(WebElement tabSheet) {
        this.tabSheet = tabSheet;
    }

    /**
     * Returns a list with the captions of the tab sheet.
     * 
     * @return List of tab sheet captions.
     */
    public List<String> getCaptions() {
        List<WebElement> tabSheetCaptions = getCaptionWebElements();
        List<String> captions = new ArrayList<String>();
        for (WebElement tabSheetCaption : tabSheetCaptions) {
            captions.add(tabSheetCaption.getText());
        }

        return captions;
    }

    private List<WebElement> getCaptionWebElements() {
        List<WebElement> tabSheetCaptions = tabSheet.findElements(By
                .className("v-captiontext"));
        return tabSheetCaptions;
    }

    public String getSelectedCaption() {
        WebElement tabSheetSelectedCaption = tabSheet
                .findElement(By
                        .cssSelector(".v-tabsheet-tabitemcell-selected .v-tabsheet-tabitem .v-captiontext"));
        return tabSheetSelectedCaption.getText();
    }

    /**
     * Does a mouse click on the caption with the given index. The index is the
     * same as in the result of {@link #getCaptions()}.
     * 
     * @param index
     *            the (zero based) index of the caption to click on.
     */
    public void click(int index) {
        List<WebElement> tabSheetCaptions = getCaptionWebElements();
        tabSheetCaptions.get(index).click();
    }

    /**
     * Returns the {@link org.openqa.selenium.WebElement} with the current
     * content of the tab sheet.
     * 
     * @return the content.
     */
    public WebElement getContent() {
        return tabSheet.findElement(By.className("v-tabsheet-content"));
    }

}
