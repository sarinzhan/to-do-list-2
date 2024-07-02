package com.example.todolist.configuration;

import com.example.todolist.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CorsConfigurationSource corsConfigurationSource
    ) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers( "/user/**").permitAll()
                        .anyRequest().authenticated()
                );

        httpSecurity
                .httpBasic(Customizer.withDefaults())
                .cors(x -> x.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable);


        httpSecurity
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();

    }


    /**
     * Конфигурирует параметры CORS (Cross-Origin Resource Sharing) для приложения.
     *
     * Этот метод создает и настраивает объект {@link CorsConfiguration}, который позволяет всем
     * источникам, методам и заголовкам выполнять кросс-доменные запросы к приложению.
     * Затем регистрирует эту конфигурацию для всех путей (/**) в {@link UrlBasedCorsConfigurationSource}.
     *
     * @return настроенный источник конфигурации CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
