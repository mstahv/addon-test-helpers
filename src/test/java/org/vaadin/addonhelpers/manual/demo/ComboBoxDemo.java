package org.vaadin.addonhelpers.manual.demo;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.vaadin.addonhelpers.AbstractTest;

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
        ComboBox<String> comboBox = new ComboBox("A combobox", getContainer("Value "));
        comboBox.setId("combobox-1");

        ComboBox comboBox2 = new ComboBox("Another combobox",
                getContainer("other Value "));
        comboBox2.setId("combobox-2");

        layout.addComponent(comboBox);
        layout.addComponent(comboBox2);
        final Label selection = new Label("");
        selection.setId("selection");
        selection.setCaption("Selection");
        layout.addComponent(selection);
        
        comboBox.addValueChangeListener(event->{
            String value = event.getValue();
            selection.setValue(value);
        });

        return layout;
    }

    private Collection<String> getContainer(String prefix) {
        Set<String> values = new TreeSet<String>();
        for (int i = 0; i < 100; i++) {
            values.add(prefix + i);
        }
        return values;
    }

}
