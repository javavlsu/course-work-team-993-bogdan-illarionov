package com.company.config;

import com.company.bean.PSD;
import com.company.storage.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /*@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(IUserRepository userRepository){
        return username -> {
            var user = userRepository.findAll().stream().filter(x -> x.getUsername().equals(username)).findFirst();

            if (user != null)
                return user.get();
            throw new IllegalArgumentException("User " + username + " not found!");
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/account/").authenticated()
                .requestMatchers("/", "/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/");

        return  httpSecurity.build();


 MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("/casino-spring-mvc");

        var str = mvcMatcherBuilder.pattern("/admin");


httpSecurity
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(mvcMatcherBuilder.pattern("/admin")).denyAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/account")).denyAll()
                );


return httpSecurity.authorizeHttpRequests()
                .requestMatchers("/casino-spring-mvc/admin")
                .authenticated()
                .and()
                .build();


        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        //.requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/casino-spring-mvc/admin").denyAll()
                        .anyRequest().authenticated()
                );

        return httpSecurity.build();

        //return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    public UserDetailsService userDetailsService(IUserRepository userRepository) {
        return username -> {
            var user = userRepository.findAll().stream().filter(x -> x.getUsername().equals(username)).findFirst().get();
            if (user != null) return user;
            throw new UsernameNotFoundException("User " + username + " not found!");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                /*.requestMatchers("/account/lk/admin/").hasRole("Admin")
                .requestMatchers("/account/lk/moderator/").hasRole("Moderator")*/
                .requestMatchers("/account/profile/index").authenticated()
                .requestMatchers("/", "/**", "/js/**", "/css/**").permitAll()

                /*.requestMatchers("/account/login").permitAll()
                .anyRequest().authenticated()*/
                .and()
                .formLogin()
                .loginPage("/account/login")
                .defaultSuccessUrl("/account/index")
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


