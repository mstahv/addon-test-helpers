package org.vaadin.addonhelpers;

import com.vaadin.annotations.Widgetset;
import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

public class TServer {

    public Server startServer() throws Exception {
        return startServer(getPort());
    }

    public Server startServer(int port) throws Exception {

        final AbstractUIProviderImpl uiprovider = new AbstractUIProviderImpl();

        Server server = new Server();

        final Connector connector = new SelectChannelConnector();

        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        WebAppContext context = new WebAppContext();
        VaadinServlet vaadinServlet = new VaadinServlet() {
            @Override
            public void init(ServletConfig servletConfig) throws ServletException {
                super.init(servletConfig);
                getService().addSessionInitListener(new SessionInitListener() {
                    @Override
                    public void sessionInit(SessionInitEvent event) throws ServiceException {
                        event.getSession().addUIProvider(uiprovider);
                    }
                });
            }
        };

        ServletHolder servletHolder = new ServletHolder(
                vaadinServlet);
        Widgetset annotation = this.getClass().getAnnotation(Widgetset.class);
        if (annotation != null) {
            servletHolder.setInitParameter("widgetset", annotation.value());
        }

        File file = new File("target/testwebapp");
        context.setWar(file.getPath());
        context.setContextPath("/");

        context.addServlet(servletHolder, "/*");
        server.setHandler(context);
        server.start();
        return server;
    }

    protected int getPort() {
        return 9998;
    }
}
