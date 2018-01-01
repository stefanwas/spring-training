package com.stefan.training.spring.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication
@EnableOAuth2Sso
//@EnableWebSecurity
//@PropertySource({"classpath:/application.properties", "classpath:/config/application-dev.properties"})
public class Application extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/login**", "/webjars/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                        .permitAll()
                .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

//        http.authorizeRequests()
//                .antMatchers("/hello").permitAll() // #4
//                .antMatchers("/index.html").permitAll() // #4
//                .antMatchers("/admin").hasRole("ADMIN") // #6
//                .anyRequest().authenticated() // 7
//                .and()
//                .formLogin().loginPage("/login").permitAll();


    }


}

