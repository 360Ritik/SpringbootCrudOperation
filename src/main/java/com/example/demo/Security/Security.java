package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.swing.text.PlainDocument;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Security {

   @Bean
    public UserDetailsService  GetUserDetailservice() {


        return  new UserInfoUserDetailService() ;
    }

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() throws Exception{

       return PasswordEncoderFactories.createDelegatingPasswordEncoder();


    }

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/save","/welcome").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/getall").authenticated()
                .and()

                .formLogin()
                .and().build() ;
   }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider() ;
        authenticationProvider.setUserDetailsService(GetUserDetailservice());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return  authenticationProvider ;
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
       AuthenticationManagerBuilder authenticationManagerBuilder= http.getSharedObject(AuthenticationManagerBuilder.class);
       authenticationManagerBuilder.authenticationProvider(authenticationProvider()) ;

       return authenticationManagerBuilder.build() ;

    }




    }

