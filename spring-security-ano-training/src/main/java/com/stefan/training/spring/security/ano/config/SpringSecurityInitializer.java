package com.stefan.training.spring.security.ano.config;


import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


/**
 * This is the equivalent filter chain configuration of:
 *
 * <filter>
 *      <filter-name>springSecurityFilterChain</filter-name>
 *      <filter-class>org.springframework.web.filter.DelegatingFilterProxy
 *      </filter-class>
 * </filter>
 *
 * <filter-mapping>
 *      <filter-name>springSecurityFilterChain</filter-name>
 *      <url-pattern>/*</url-pattern>
 * </filter-mapping>
 */

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
