package com.ensias.ensiasattendease.config;

import com.ensias.ensiasattendease.models.Permission;
import com.ensias.ensiasattendease.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;


import com.ensias.ensiasattendease.repositories.UserRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration  {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors().disable();
        httpSecurity
                .csrf()
                .disable()
                .cors().and() // added this
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")//ADD HERE ENDPOINTS THAT YOU DON'T NEED TO AUTHENTICATE TO USE THEM(WHITELIST)
//
//                .requestMatchers("/**")//ADD HERE ENDPOINTS THAT YOU DON'T NEED TO AUTHENTICATE TO USE THEM(WHITELIST)



                .permitAll()

                //SECURE HERE ENDPOINTS , EVERY ROLE & ITS PERMISSION


//                .requestMatchers("/students/**").hasAnyRole(Role.ADMIN.name())
//                .requestMatchers(HttpMethod.GET, "/students/**").hasAnyAuthority(Permission.STUDENT_READ.name())
//                .requestMatchers(HttpMethod.POST, "/students/**").hasAnyAuthority(Permission.STUDENT_CREATE.name())
//                .requestMatchers(HttpMethod.PUT, "/students/**").hasAnyAuthority(Permission.STUDENT_UPDATE.name())
//                .requestMatchers(HttpMethod.DELETE, "/students/**").hasAnyAuthority(Permission.STUDENT_DELETE.name())
//                .requestMatchers(HttpMethod.PATCH, "/students/**").hasAnyAuthority(Permission.STUDENT_PATCH.name())
//
//                .requestMatchers("/students/**").hasAnyRole(Role.STUDENT.name(),Role.TEACHER.name())
//                .requestMatchers(HttpMethod.GET, "/students/**").hasAnyAuthority(Permission.STUDENT_READ.name())


                /* .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                 .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                 .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                 .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                 .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
        return httpSecurity.build();
    }



    //I added this

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }



}
