package com.anilchauhan.cashrich_assignment.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
      http
          .authorizeHttpRequests(requests -> requests
              .requestMatchers(new AntPathRequestMatcher("/api/signup", "/api/auth/login")).permitAll()
              .requestMatchers("/api/call-third-party").authenticated()
              .anyRequest().authenticated())
          .httpBasic();
      return http.build();
    }
}