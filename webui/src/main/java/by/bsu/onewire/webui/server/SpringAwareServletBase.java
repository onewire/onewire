package by.bsu.onewire.webui.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Servlet base class with spring application context initialization.
 */
@SuppressWarnings("serial")
public class SpringAwareServletBase extends RemoteServiceServlet {
    /**
     * Populates spring properties.
     */
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        // Dependency injection
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory beanFactory = wac.getAutowireCapableBeanFactory();
        beanFactory.autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
        beanFactory.initializeBean(this, this.getClass().getName());
    }

}
