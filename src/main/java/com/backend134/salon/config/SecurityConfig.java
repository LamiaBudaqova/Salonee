package com.backend134.salon.config;

import com.backend134.salon.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/front/**").permitAll()
                        .requestMatchers("/admin/css/**", "/admin/js/**", "/admin/images/**", "/admin/vendors/**", "/front/**").permitAll()

                        .requestMatchers("/", "/about", "/service/**", "/services/**",
                                "/category/**", "/price", "/gallery/**", "/team",
                                "/blog/**", "/contact",
                                "/testimonial", "/testimonial/**",
                                "/register", "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/contact").permitAll()
                        .requestMatchers(HttpMethod.POST, "/contact").permitAll()
                        .requestMatchers(HttpMethod.POST, "/testimonial").permitAll()

                        // ADMIN panel - yalnız ADMIN görə bilər
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            // 1. URL-də continue param varsa
                            String continueParam = request.getParameter("continue");
                            if (continueParam != null && !continueParam.isBlank()) {
                                response.sendRedirect(continueParam);
                                return;
                            }

                            // 2. Spring-in saxladığı request varsa
                            HttpSessionRequestCache cache = new HttpSessionRequestCache();
                            SavedRequest savedRequest = cache.getRequest(request, response);
                            if (savedRequest != null) {
                                response.sendRedirect(savedRequest.getRedirectUrl());
                                return;
                            }

                            // 3. Referer başlığına bax
                            String referer = request.getHeader("Referer");
                            if (referer != null && !referer.contains("/login") && !referer.contains("/register")) {
                                response.sendRedirect(referer);
                            } else {
                                // fallback
                                response.sendRedirect("/");
                            }
                        })
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
