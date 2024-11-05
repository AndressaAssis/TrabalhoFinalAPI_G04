package org.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
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
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADM"))
				.csrf(csfr -> csfr.disable())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager usuariosDetalhes() {
		UserDetails usuario = User.builder()
				.username("grupo4")
				.password(encoder().encode("1234"))
				.roles("ADM").build();

		UserDetails usuario2 = User.builder()
				.username("grupo")
				.password(encoder().encode("1234"))
				.roles("BOBAO").build();
		return new InMemoryUserDetailsManager(usuario, usuario2);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}