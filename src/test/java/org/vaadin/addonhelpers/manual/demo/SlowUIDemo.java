package org.vaadin.addonhelpers.manual.demo;

import org.vaadin.addonhelpers.AbstractTest;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.*;

public class SlowUIDemo extends AbstractTest {
    @Override
    public String getDescription() {
        return "A button which wastes some time before show the result.";
    }

    @Override
    public Component getTestComponent() {
        final Label label = new Label();
        label.setId("result");
        MButton slowButton = new MButton("Compute", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                label.setValue("42");
            }
        });
        slowButton.setId("slowComputeButton");

        MButton fastButton = new MButton("Compute fast", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                label.setValue("42");
            }
        });
        fastButton.setId("fastComputeButton");
        return new MVerticalLayout(slowButton, fastButton, label);
    }
}
