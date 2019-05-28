package com.wyc.vote.config;

import com.wyc.vote.security.CustAuthenticationProvider;
import com.wyc.vote.security.CustAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustAuthenticationProvider custAuthenticationProvider;

    @Autowired
    CustAuthenticationSuccessHandler custAuthenticationSuccessHandler;

    @Override
    public AuthenticationManager authenticationManagerBean() {
        try {
            return super.authenticationManagerBean();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(){
        UsernamePasswordAuthenticationFilter upaf = new UsernamePasswordAuthenticationFilter();
        upaf.setSessionAuthenticationStrategy(sas());
        upaf.setAuthenticationManager(authenticationManagerBean());
        return upaf;
    }


    @Bean
    public CompositeSessionAuthenticationStrategy sas(){

        List<SessionAuthenticationStrategy> delegateStrategies = new ArrayList<>();
        delegateStrategies.add(new SessionFixationProtectionStrategy());
        delegateStrategies.add(new RegisterSessionAuthenticationStrategy(sessionRegistry()));

        ConcurrentSessionControlAuthenticationStrategy cscas
                =  new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        cscas.setMaximumSessions(1);
        cscas.setExceptionIfMaximumExceeded(true);
        delegateStrategies.add(cscas);

        return  new CompositeSessionAuthenticationStrategy(delegateStrategies);
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter(){
        return new ConcurrentSessionFilter(sessionRegistry(),simpleRedirectSessionInformationExpiredStrategy());
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public SimpleRedirectSessionInformationExpiredStrategy simpleRedirectSessionInformationExpiredStrategy(){
        return  new SimpleRedirectSessionInformationExpiredStrategy("/");
    }

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
                .successHandler(custAuthenticationSuccessHandler)
                //.successForwardUrl("/vote/main")// 登陆成功后跳转的页面
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
