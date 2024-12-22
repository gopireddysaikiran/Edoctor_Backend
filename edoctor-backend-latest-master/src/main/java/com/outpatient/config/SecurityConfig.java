//package com.outpatient.config;
//
//import com.outpatient.entity.User;
//import com.outpatient.repository.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.cors.CorsConfigurationSource;
//import java.util.List;
//
//@Configuration
//public class SecurityConfig {
//
//    private final UserRepository userRepository;
//
//    public SecurityConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in testing
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS with custom configuration
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/users/register").permitAll()
//                        .requestMatchers("/api/users/verify-otp/**").permitAll()
//                        .requestMatchers("/api/users/login").permitAll()
//                        .requestMatchers("/api/appointments/doctor/**").hasRole("DOCTOR") // Allow only doctors
//                        .requestMatchers("/api/appointments/user/**").hasRole("PATIENT")
//                        .requestMatchers("/api/appointments/**").hasAnyRole("PATIENT", "DOCTOR")
//                        .requestMatchers("/api/doctors/**").permitAll()
//                        .requestMatchers("/api/users/username/**").hasAnyRole("DOCTOR", "PATIENT")
//                        .requestMatchers("/api/chatbot/respond").permitAll()
//                        .requestMatchers("/api/appointments/**").hasAnyRole("PATIENT", "DOCTOR")
//                        .requestMatchers("/api/availability/**").hasAnyRole("PATIENT", "DOCTOR")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic() // Enable Basic Authentication
//                .and()
//                .formLogin().disable(); // Disable form login for simplicity in APIs
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:3000"); // Allow React app origin
//        configuration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
//        configuration.addAllowedHeader("*"); // Allow all headers
//        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)
//
//        // Apply the configuration to all endpoints
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            User user = userRepository.findByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
//            );
//        };
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
package com.outpatient.config;

import com.outpatient.entity.User;
import com.outpatient.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in testing
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS with custom configuration
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/api/users/verify-otp/**").permitAll()
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers("/api/appointments/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/api/appointments/user/**").hasRole("PATIENT")
                        .requestMatchers("/api/appointments/**").hasAnyRole("PATIENT", "DOCTOR")
                        .requestMatchers("/api/doctors/**").permitAll()
                        .requestMatchers("/api/payments/**").permitAll()
    
                        .requestMatchers("/api/users/username/**").hasAnyRole("DOCTOR", "PATIENT")
                        .requestMatchers("/api/chatbot/respond").permitAll()
                        .requestMatchers("/api/appointments/**").hasAnyRole("PATIENT", "DOCTOR")
                        .requestMatchers("/api/availability/**").hasAnyRole("PATIENT", "DOCTOR")
                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Session will be created only when necessary
                .and()
                .httpBasic(); // Use HTTP Basic Authentication

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Allow React app origin
        configuration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
        configuration.addAllowedHeader("*"); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)

        // Apply the configuration to all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
