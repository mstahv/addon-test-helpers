package org.vaadin.addonhelpers.manual.demo;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

public class PathParameterDemo extends AbstractTest {
    
    @Override
    public String getDescription() {
        return "Simple demo for the testserver";
    }

    @Override
    public Component getTestComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();
        
        String path = Page.getCurrent().getLocation().getPath();
        
        verticalLayout.addComponent(new Label("Curent path: " + path));
        
        verticalLayout.addComponent(new Link("Add foobar", new ExternalResource(path + "/foo/bar")));

        return verticalLayout;
    }

}
