package com.stefan.training.spring.security.rest;//package com.stefan.training;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan("com.stefan.training")
//public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//    @Autowired
//    private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN")
//                .and()
//                .withUser("user").password("userPass").roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/foos").authenticated()
//                .and()
//                .formLogin()
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                .and()
//                .logout();
//    }
//
//    @Bean
//    public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler(){
//        return new MySavedRequestAwareAuthenticationSuccessHandler();
//    }
//    @Bean
//    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
//        return new SimpleUrlAuthenticationFailureHandler();
//    }
//}
