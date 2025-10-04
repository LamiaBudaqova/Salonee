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
                            // Giriş etmiş istifadəçinin rollarını yoxlayaq
                            var authorities = authentication.getAuthorities();
                            boolean isAdmin = authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

                            if (isAdmin) {
                                response.sendRedirect("/admin"); // Admin panelə yönləndir
                            } else {
                                response.sendRedirect("/"); // Adi user ana səhifəyə
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
