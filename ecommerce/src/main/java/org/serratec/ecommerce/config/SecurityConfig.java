package org.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/pedidos").permitAll()
                .requestMatchers(HttpMethod.POST, "/pedidos").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/pedidos").permitAll()
                .requestMatchers(HttpMethod.PUT, "/pedidos").permitAll()
                .requestMatchers(HttpMethod.GET, "/pedidos/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/pedidos/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/pedidos/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/pedidos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/clientes").permitAll()
                .requestMatchers(HttpMethod.POST, "/clientes").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/clientes").permitAll()
                .requestMatchers(HttpMethod.PUT, "/clientes").permitAll()
                .requestMatchers(HttpMethod.GET, "/clientes/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/clientes/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/clientes/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/clientes/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/itensPedido").permitAll()
                .requestMatchers(HttpMethod.POST, "/itensPedido").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/itensPedido").permitAll()
                .requestMatchers(HttpMethod.PUT, "/itensPedido").permitAll()
                .requestMatchers(HttpMethod.GET, "/itensPedido/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/itensPedido/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/itensPedido/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/itensPedido/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/jogos").permitAll()
                .requestMatchers(HttpMethod.POST, "/jogos").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/jogos").permitAll()
                .requestMatchers(HttpMethod.PUT, "/jogos").permitAll()
                .requestMatchers(HttpMethod.GET, "/jogos/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/jogos/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/jogos/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/jogos/{id}").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADM")
                .requestMatchers("/auth/login", "/criarConta").permitAll()
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173"); 
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
