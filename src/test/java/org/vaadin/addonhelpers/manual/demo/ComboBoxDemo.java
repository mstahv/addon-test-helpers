package org.vaadin.addonhelpers.manual.demo;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ComboBoxDemo extends AbstractTest {
    @Override
    public String getDescription() {
        return "Demo with combobox";
    }

    @Override
    public Component getTestComponent() {
        VerticalLayout layout = new VerticalLayout();
        ComboBox comboBox = new ComboBox("A combobox", getContainer());
        comboBox.setId("comboBox");
        comboBox.setNewItemsAllowed(true);
        layout.addComponent(comboBox);
        final Label selection = new Label("");
        selection.setId("selection");
        selection.setCaption("Selection");
        layout.addComponent(selection);

        comboBox.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                String value = null;
                if (event.getProperty().getValue() != null) {
                    value = event.getProperty().getValue().toString();
                }
                selection.setValue(value);
            }
        });
        return layout;
    }

    private Collection<String> getContainer() {
        Set<String> values = new TreeSet<String>();
        for (int i = 0; i < 100; i++) {
            values.add("Value " + i);
        }
        return values;
    }

}
