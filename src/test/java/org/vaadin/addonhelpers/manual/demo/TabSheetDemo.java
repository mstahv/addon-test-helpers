package org.vaadin.addonhelpers.manual.demo;

import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.*;

public class TabSheetDemo extends AbstractTest {

    @Override
    public String getDescription() {
        return "Demo for testing TabSheets";
    }

    @Override
    public Component getTestComponent() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setId("tabsheet");
        Label label1 = new Label("Tab1");
        label1.setStyleName("tab-content-label");
        tabSheet.addTab(label1, "Tab 1 Caption");
        Label label2 = new Label("Tab2");
        label2.setStyleName("tab-content-label");
        tabSheet.addTab(label2, "Tab 2 Caption");
        return tabSheet;
    }
}
