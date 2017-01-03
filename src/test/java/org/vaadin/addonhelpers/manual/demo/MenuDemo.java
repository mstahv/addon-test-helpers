package org.vaadin.addonhelpers.manual.demo;

import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuDemo extends AbstractTest {
    @Override
    public String getDescription() {
        return "Demo for testing menu navigation";
    }

    @Override
    public Component getTestComponent() {
        final Label selection = new Label("-");
        selection.setId("selection");

        final MenuBar.Command mycommand = new MenuBar.Command() {

            public void menuSelected(MenuItem selectedItem) {
                selection.setValue("Ordered a " + selectedItem.getText()
                        + " from menu.");
            }
        };
        MenuBar barmenu = new MenuBar();
        barmenu.setId("menubar");
        barmenu.setAutoOpen(true);
        MenuItem drinks = barmenu.addItem("Beverages", null, null);
        MenuItem hots = drinks.addItem("Hot", null, null);
        hots.addItem("Tea", null, mycommand);
        hots.addItem("Coffee", null, mycommand);
        MenuItem servs = barmenu.addItem("Services", null, mycommand);
        servs.addItem("Car Service", null, mycommand);
        VerticalLayout vl = new VerticalLayout(barmenu, selection);
        vl.setSizeFull();
        return vl;
    }
}
