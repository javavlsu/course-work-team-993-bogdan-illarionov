package com.company.config;

import com.company.bean.PSD;
import com.company.models.CasinoUserDetailsService;
import com.company.storage.jpa.IUserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CasinoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()

                .requestMatchers("/account/profile/index").authenticated()
                .requestMatchers("/bets").hasAnyAuthority("Player")
                .requestMatchers("/account/manage").hasAnyAuthority("Admin")
                .requestMatchers("/lots/manage").hasAnyAuthority("Admin")
                .requestMatchers("/bonus/*").hasAnyAuthority("Admin")
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/account/login")
                .defaultSuccessUrl("/index")

                .and() // Logout config
                .logout()
                .logoutUrl("/account/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/account/login")

                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PSD();
    }

    @Bean(name = "mvcHandlerMappingIntrospector")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
}


