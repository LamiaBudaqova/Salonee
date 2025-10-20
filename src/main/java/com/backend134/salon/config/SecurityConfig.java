package com.backend134.salon.config;

import com.backend134.salon.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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

                        // ✅ 1. Rezervasiya — login olmadan GET və POST icazə
                        .requestMatchers("/booking", "/booking/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/booking").permitAll()

                        // ✅ 2. Statik resurslar (ön və admin)
                        .requestMatchers(
                                "/css/**", "/js/**", "/img/**", "/images/**",
                                "/uploads/**",
                                "/front/**", "/front/lib/**", "/front/css/**", "/front/js/**",
                                "/admin/css/**", "/admin/js/**",
                                "/staff/css/**", "/staff/js/**"
                        ).permitAll()

                        // ✅ 3. Açıq səhifələr (login tələb etmir)
                        .requestMatchers(
                                "/", "/about", "/service/**", "/services/**",
                                "/category/**", "/price", "/gallery/**", "/team",
                                "/blog/**", "/contact", "/contact/**",
                                "/testimonial", "/testimonial/**",
                                "/register", "/login"
                        ).permitAll()

                        // ✅ 4. Admin və staff üçün qorunan routelar
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/staff/**").hasAnyRole("STAFF", "ADMIN")

                        // ✅ 5. Qalan hər şey login tələb edir
                        .anyRequest().authenticated()
                )

                // ✅ Login formu
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            var authorities = authentication.getAuthorities();
                            boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                            boolean isStaff = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"));
                            var principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
                            String username = principal.getUsername();

                            if (isAdmin) {
                                response.sendRedirect("/admin");
                            } else if (isStaff) {
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

                // ✅ Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )

                .exceptionHandling(ex -> ex.accessDeniedPage("/error-403"));

        return http.build();
    }
}

