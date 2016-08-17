/*
 * Copyright 2012 Vaadin Community.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.addonhelpers;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mattitahvonenitmill
 */
@Theme("valo")
public class TListUi extends UI {

    private IndexedContainer testClassess;

    @Override
    protected void init(VaadinRequest request) {
        loadTestClasses(this);
    }

    private void loadTestClasses(TListUi aThis) {
        if (testClassess != null) {
            return;
        }
        testClassess = listTestClasses();
        Table table = new Table("Test cases", testClassess);
        table.setVisibleColumns("name", "description");
        table.addGeneratedColumn("name", new Table.ColumnGenerator() {
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                String name = (String) source.getItem(itemId).getItemProperty(
                        columnId).getValue();
                Class clazz = (Class) source.getItem(itemId).getItemProperty("clazz").getValue();
                Link link = new Link();
                link.setResource(new ExternalResource("/" + clazz.getName()));
                link.setCaption(name);
                link.setTargetName("_new");
                return link;
            }
        });
        table.addGeneratedColumn("description", new Table.ColumnGenerator() {
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                String description = (String) source.getItem(itemId).
                        getItemProperty(columnId).getValue();
                return new Label(description);
            }
        });
        table.setSizeFull();
        table.setColumnExpandRatio("description", 1);
        VerticalLayout verticalLayout = new VerticalLayout();
        TextField filter = new TextField();
        filter.setInputPrompt("Filter list");
        filter.addTextChangeListener(new TextChangeListener() {
            @Override
            public void textChange(TextChangeEvent event) {
                String text = event.getText();
                testClassess.removeAllContainerFilters();
                testClassess.addContainerFilter("name", text, true, false);
            }
        });
        verticalLayout.addComponent(filter);
        filter.focus();
        verticalLayout.addComponent(table);
        verticalLayout.setSizeFull();
        verticalLayout.setExpandRatio(table, 1);
        verticalLayout.setMargin(true);
        setContent(verticalLayout);
    }

    public static IndexedContainer listTestClasses() {
        final IndexedContainer indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("name", String.class, "");
        indexedContainer.addContainerProperty("description", String.class, "");
        indexedContainer.addContainerProperty("clazz", Class.class, null);
        final File testroot = getTestRoot();

        if (testroot.exists()) {
            try {
                Files.walkFileTree(testroot.toPath(),
                        new SimpleFileVisitor<Path>() {

                            @Override
                            public FileVisitResult visitFile(Path f,
                                    BasicFileAttributes attrs) {
                                if(!f.toString().endsWith(".java")) {
                                    return FileVisitResult.CONTINUE;
                                }
                                try {
                                    String name = f.getFileName().toString().replace(".java", "");
                                    Path packageDir = testroot.toPath().
                                    relativize(f.getParent());
                                    String packageName = packageDir.toString().
                                    replaceAll("[/\\\\]", ".");
                                    if(!packageName.isEmpty()) {
                                        packageName += ".";
                                    }

                                    Class<?> forName = Class.forName(
                                            packageName + name);
                                    addTest(indexedContainer, name, forName);
                                } catch (Exception e) {
                                    // e.printStackTrace();
                                    // e.printStackTrace();
                                }
                                return FileVisitResult.CONTINUE;
                            }

                        }
                );
            } catch (IOException ex) {
                Logger.getLogger(TListUi.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        return indexedContainer;
    }

    protected static File getTestRoot() {
        return new File("src/test/java/");
    }

    static void addTest(IndexedContainer indexedContainer, String simpleName,
                    Class<?> forName) throws InstantiationException, IllegalAccessException {
        if (UI.class.isAssignableFrom(forName)) {
            UI newInstance = (UI) forName.newInstance();
            Object id = indexedContainer.addItem();
            Item item = indexedContainer.getItem(id);
            item.getItemProperty("clazz").setValue(forName);
            item.getItemProperty(
                    "name").setValue(simpleName);
            item.getItemProperty(
                    "description").setValue(newInstance.
                            getDescription());
        }
    }

}
