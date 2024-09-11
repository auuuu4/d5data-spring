package com.d5data.d5dataspring.config;

import com.d5data.d5dataspring.component.D5DataAuthenticationEntryPoint;
import com.d5data.d5dataspring.component.D5DataAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration

public class SecurityConfig {
    @Autowired
    D5DataAuthenticationEntryPoint d5DataAuthenticationEntryPoint;
    @Autowired
    D5DataAuthenticationSuccessHandler d5DataAuthenticationSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin.loginProcessingUrl("/").permitAll()
                        .successHandler(d5DataAuthenticationSuccessHandler)
                )
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .anyRequest().authenticated();
                });
        return httpSecurity.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.withUsername("test")
                .password(passwordEncoder().encode("123456"))
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
