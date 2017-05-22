package com.amur.configuration;

import com.amur.areas.users.userService.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/",
//                        "/users/register/**",
//                        "/bootstrap/**",
//                        "/jquery/**",
//                        "/css/**",
//                        "/images/**",
//                        "/products/**",
//                        "/categories/**",
//                        "/connect/**").permitAll()

                .anyRequest().permitAll()
        .and()
                .formLogin().loginPage("/users/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
        .and()
                .rememberMe()
                .rememberMeCookieName("RememberMe")
                .rememberMeParameter("rememberMe")
                .key("VerySecretKey")
                .tokenValiditySeconds(100000)
        .and()
                .logout().logoutSuccessUrl("/users/login?logout").permitAll()
        .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
        .and()
                .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public CsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository repository =new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
