package org.vaadin.addonhelpers.manual.demo;

import org.vaadin.addonhelpers.TServer;

/**
 * Run this class to run the demo server. You can access the demos at
 * http://localhost:9998
 */
public class Demo extends TServer {

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        demo.startServer();
    }
}