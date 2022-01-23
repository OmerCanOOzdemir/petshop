package com.example.petshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select email as principal, password as credentials,true from user where email=?")
                .authoritiesByUsernameQuery("select email as principal, role_name as role from user where email=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("Role_");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/**","/login","/css/**","/webjars/**").permitAll().
                anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/").and().logout()
                .logoutSuccessUrl("/");
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
