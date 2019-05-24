package com.wyc.vote.config;

import com.wyc.vote.security.CustAuthenticationProvider;
import com.wyc.vote.security.CustAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustAuthenticationProvider custAuthenticationProvider;

    @Autowired
    CustAuthenticationSuccessHandler custAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
                .antMatchers("/images/**","/webjars/**","/register","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                //.successHandler(custAuthenticationSuccessHandler)
                .successForwardUrl("/vote/main")// 登陆成功后跳转的页面
                //.defaultSuccessUrl("/vote/main3")

                .failureUrl("/error")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(custAuthenticationProvider);
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("admin")
//                .and()
//                .withUser("test").password("test123").roles("user");
    }
}
