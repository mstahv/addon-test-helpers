package org.vaadin.addonhelpers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.converter.ConverterFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.Form;
import com.vaadin.v7.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.v7.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Item;

/**
 * A simple helper to edit properties in component hierarchy during testing.
 */
public class ComponentEditor extends Window {

    private Tree tree;
    private HorizontalSplitPanel horizontalSplitPanel;

    public ComponentEditor(Component root) {
        setCaption("Component editor");
        setWidth("600px");
        setHeight("250px");
        setPositionX(300);
        setPositionY(100);

        horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setSplitPosition(30);
        tree = new Tree();
        horizontalSplitPanel.setFirstComponent(tree);
        tree.setSelectable(true);
        tree.setImmediate(true);
        tree.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                Component c = (Component) event.getProperty().getValue();
                setEditedComponent(c);
            }
        });
        tree.expandItemsRecursively(root);
        setContent(horizontalSplitPanel);
        setEditedComponent(root);

    }

    static Collection<String> noneditablefields = Arrays.asList(
            "componentError", "data", "debugId", "errorHandler", "icon",
            "parent", "propertyDataSource", "type", "window");

    protected void setEditedComponent(Component c) {
        Form form = new Form();
        form.setImmediate(true);
        form.setFormFieldFactory(new FormFieldFactory() {

            public Field createField(Item item, Object propertyId,
                    Component uiContext) {
                if (noneditablefields.contains(propertyId)) {
                    return null;
                }
               return new DefaultFieldFactory() {

                    @Override
                    public Field<?> createField(Item item, Object propertyId,
                            Component uiContext) {
                        final Field<?> createField = super.createField(item, propertyId, uiContext);
                        if(createField instanceof TextField) {
                            if(item.getItemProperty(propertyId).getType() != String.class) {
                                // Added to workaround V7 stupidities
                                // Probably blocks some modifieable fields...
                                return null;
                            }
                        }
                        return createField;
                    }
                   
               }.createField(item, propertyId,
                        uiContext);

            }
        });
        BeanItem beanItem = new BeanItem(c);
        form.setItemDataSource(beanItem);
        horizontalSplitPanel.setSecondComponent(form);
    }

    public void add(ComponentContainer root) {
        tree.addItem(root);
        setTreeCaption(root);
        Iterator<Component> componentIterator = root.iterator();
        while (componentIterator.hasNext()) {
            Component next = componentIterator.next();
            if (next instanceof ComponentContainer) {
                add((ComponentContainer) next);
            } else {
                tree.addItem(next);
                setTreeCaption(next);
                if (next instanceof Form) {
                    Layout layout = ((Form) next).getLayout();
                    add(layout);
                    tree.setParent(layout, next);
                    layout = ((Form) next).getFooter();
                    add(layout);
                    tree.setParent(layout, next);
                }
            }
            tree.setParent(next, root);
        }

    }

    private void setTreeCaption(Component root) {
        tree.setItemCaption(root, root.getClass().getSimpleName());
    }
}
