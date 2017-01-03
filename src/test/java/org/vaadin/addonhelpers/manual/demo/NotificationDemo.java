package org.vaadin.addonhelpers.manual.demo;

import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;

public class NotificationDemo extends AbstractTest {

    @Override
    public Component getTestComponent() {
        Button warnButton = new Button("warn", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Warning", "Warning description",
                        Type.WARNING_MESSAGE);
            }
        });
        Button trayButton = new Button("tray", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Tray", "Tray description",
                        Type.TRAY_NOTIFICATION);
            }
        });
        Button humanizedButton = new Button("humanized", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Humanized", "Humanized description",
                        Type.HUMANIZED_MESSAGE);
            }
        });
        Button errorButton = new Button("error", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Error", "Error description",
                        Type.ERROR_MESSAGE);
            }
        });
        warnButton.setId("warn-button");
        trayButton.setId("tray-button");
        humanizedButton.setId("humanized-button");
        errorButton.setId("error-button");
        return new VerticalLayout(warnButton, trayButton, humanizedButton,
                errorButton);
    }

    @Override
    public String getDescription() {
        return "Notification Demos";
    }
}
