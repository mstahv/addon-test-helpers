package org.vaadin.addonhelpers.manual.demo;

import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addonhelpers.AbstractTest;


public class OptionGroupDemo extends AbstractTest {

    @Override
    public Component getTestComponent() {
        // A single-select radio button group
        RadioButtonGroup single = new RadioButtonGroup("Single Selection");
        single.setItems("Single", "Sola", "Yksi");
        single.setItemEnabledProvider(s->!"Sola".equals(s));
        single.setId("single-optiongroup");

        // A multi-select check box group
        CheckBoxGroup<String> multi = new CheckBoxGroup<>("Multiple Selection");
        multi.setItems("Many", "Muchos", "Monta");
        multi.setItemEnabledProvider(s->!s.equals("Monta"));
        multi.setId("multi-optiongroup");

        return new VerticalLayout(single, multi);
    }
}
