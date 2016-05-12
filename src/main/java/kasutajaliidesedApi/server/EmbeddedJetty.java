package kasutajaliidesedApi.server;

import com.google.inject.servlet.GuiceFilter;
import kasutajaliidesedApi.config.KasutajaliidesedApiApplication;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.net.URI;
import java.util.EnumSet;

public class EmbeddedJetty {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedJetty.class);
    private static EmbeddedJetty instance;
    private Server server;

    private EmbeddedJetty() {
    }

    public static EmbeddedJetty instance() {
        if (instance == null) {
            instance = new EmbeddedJetty();
        }

        return instance;
    }

    public void start(int port) throws Exception {
        synchronized (this) {
            if (server == null) {
                logger.info("Starting jetty on port " + port);
                server = new Server(port);
                server.setHandler(createServletContextHandler());
                server.start();
            }
        }
    }

    private ServletContextHandler createServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletContextHandler.setContextPath("/");

        addFilters(servletContextHandler);
        configureDynamicContentServlet(servletContextHandler);
        return servletContextHandler;
    }

    private void configureDynamicContentServlet(ServletContextHandler servletContextHandler) {
        ServletHolder servlet = new ServletHolder(new ServletContainer());
        servlet.setInitParameter("javax.ws.rs.Application", KasutajaliidesedApiApplication.class.getCanonicalName());
        servletContextHandler.addServlet(servlet, "/rest/*");
    }

    private void addFilters(ServletContextHandler servletContextHandler) {
        // Filter to inject object into other filters. Must be above all others.
        servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
    }

    public void stop() throws Exception {
        synchronized (this) {
            if (server == null) {
                return;
            }

            if (isRunning()) {
                server.stop();
            }

            server.destroy();
            server = null;
        }
    }

    public URI getBaseUri() {
        return server.getURI();
    }

    public boolean isRunning() {
        return server != null && server.getState() != AbstractLifeCycle.STOPPED;
    }
}