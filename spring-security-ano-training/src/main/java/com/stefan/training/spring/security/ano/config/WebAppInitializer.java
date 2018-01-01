package com.stefan.training.spring.security.ano.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * This class takes care of:
 * - servletContexts initialisation
 * - spring context initialisation
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringSecurityConfiguration.class);
        rootContext.setServletContext(servletContext);
        rootContext.refresh();

        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setParent(rootContext);
        applicationContext.register(WebAppConfiguration.class);
        applicationContext.scan("com.stefan.training.spring.security.ano");
        applicationContext.setServletContext(servletContext);
        applicationContext.refresh();


        DispatcherServlet servlet = new DispatcherServlet(applicationContext);

        ServletRegistration.Dynamic servletRegistration =  servletContext.addServlet("servlet", servlet);
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);


//        ServletRegistration.Dynamic jspRegistration =  servletContext.addServlet("jsp", servlet);
//        // option 1: "/" - to make MVC Controller work
//        // option 2: "/rest/*" - to make REST services work
//        servletRegistration.addMapping("/");
//        servletRegistration.setLoadOnStartup(1);


    }
}
