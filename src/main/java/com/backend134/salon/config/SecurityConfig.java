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
                        // ✅ FRONT statik fayllar açıq olmalıdır
                        .requestMatchers("/admin/**", "/css/**", "/js/**", "/img/**", "/images/**", "/front/**", "/front/lib/**", "/front/css/**", "/front/js/**").permitAll()

                        // ✅ Açıq (public) səhifələr
                        .requestMatchers("/", "/about", "/service/**", "/services/**",
                                "/category/**", "/price", "/gallery/**", "/team",
                                "/blog/**", "/contact", "/contact/**",
                                "/booking", "/booking/**",
                                "/testimonial", "/testimonial/**",
                                "/register", "/login").permitAll()

                        // ✅ Form POST əməliyyatları
                        .requestMatchers(HttpMethod.POST, "/contact").permitAll()
                        .requestMatchers(HttpMethod.POST, "/testimonial").permitAll()
                        .requestMatchers(HttpMethod.POST, "/booking").permitAll()

                        .requestMatchers(HttpMethod.POST, "/staff/update-status").hasAnyRole("STAFF", "ADMIN")
                        // ✅ ADMIN panel – yalnız admin görə bilər
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // ✅ STAFF (usta) panel – həm STAFF, həm ADMIN görə bilər
                        .requestMatchers("/staff/**").hasAnyRole("STAFF", "ADMIN")

                        // ✅ Qalan bütün URL-lər login tələb edir
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            var authorities = authentication.getAuthorities();

                            boolean isAdmin = authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                            boolean isStaff = authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"));

                            // Login olan istifadəçinin username (email və ya username)
                            var principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
                            String username = principal.getUsername();

                            if (isAdmin) {
                                response.sendRedirect("/admin");
                            } else if (isStaff) {
                                // 🔹 Staff ID-ni DB-dən tapırıq
                                var staffOpt = userDetailsService.findStaffByUsername(username);

                                if (staffOpt.isPresent()) {
                                    var staff = staffOpt.get();
                                    response.sendRedirect("/staff/dashboard/" + staff.getId());
                                } else {
                                    response.sendRedirect("/");
                                }
                            } else {
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
