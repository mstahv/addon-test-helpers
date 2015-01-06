package org.vaadin.addonhelpers.manual.demo;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SimpleDemo extends AbstractTest {
    private Label label = new Label("OFF");

    @Override
    public String getDescription() {
        return "Simple demo for the testserver";
    }

    @Override
    public Component getTestComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(label);
        label.setId("label");
        Button toggleButton = new Button("Click to toggle label");
        toggleButton.setId("toggleButton");
        toggleButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                String newValue;
                if (StringUtils.equals("ON", label.getValue())) {
                    newValue = "OFF";
                } else {
                    newValue = "ON";
                }
                label.setValue(newValue);
            }
        });
        verticalLayout.addComponent(toggleButton);
        return verticalLayout;
    }

}
