/*
 * Copyright 2016 mattitahvonenitmill.
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
package org.vaadin.addonhelpers.manual.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * Also plain UI classes are supported from version 1.9
 */
@Theme("valo")
public class PlainUIDemo extends UI {

    @Override
    public String getDescription() {
        return "Also plain UI classes are listed since 1.9";
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Label("Hello world!"));
    }
    
}
