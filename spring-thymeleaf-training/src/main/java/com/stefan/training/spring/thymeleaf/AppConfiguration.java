package com.stefan.training.spring.thymeleaf;//package com.stefan.training;
//
//
//import nz.net.ultraq.thymeleaf.LayoutDialect;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.thymeleaf.dialect.IDialect;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//@EnableWebMvc
//public class AppConfiguration extends WebMvcConfigurerAdapter {
//
////    @Bean
//    public ServletContextTemplateResolver templateResolver() {
//        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
//        resolver.setPrefix("/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode("HTML5");
//        return resolver;
//    }
//
////    @Bean
//    public SpringTemplateEngine templateEngine() {
//        Set<IDialect> dialectSet = new HashSet<>();
//        dialectSet.add(new LayoutDialect());
//
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver());
//        engine.setAdditionalDialects(dialectSet);
//        return engine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver viewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setOrder(1);
//        resolver.setViewNames(new String[] {"*", "js/*", "template/*"});
//        return resolver;
//    }
//
//}
