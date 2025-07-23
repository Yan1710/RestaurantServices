package com.example.Restaurante.configuration;
import com.example.Restaurante.Utils.RolEnum;
import com.example.Restaurante.components.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // DEPRECATED
   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/usuarios/login", "/api/usuarios/registro").permitAll()
                .requestMatchers("/api/Domicilio/list","/api/Domicilio/encamino","/api/Domicilio/entregado").hasAnyRole(RolEnum.DOMICILIARIO.getValor(),RolEnum.ADMINISTRADOR.getValor())
                .requestMatchers("/api/Restaurante/pedidos","/api/Restaurante/saveitems","/api/Restaurante/savepedido").hasAnyRole(RolEnum.RESTAURANTE.getValor(),RolEnum.ADMINISTRADOR.getValor())
                .requestMatchers("/api/pedidos","/api/pedidos/items","/api/pedidos/{id}","/api/pedidos/total/{cliente}","/api/pedidos/restaurantes").hasAnyRole(RolEnum.CLIENTE.getValor(),RolEnum.ADMINISTRADOR.getValor())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Filtro JWT;

        return http.build();
    }*/

    //TODO NEW
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/webjars/**",
                                "/swagger-resources/**"
                        ).permitAll()
                        .requestMatchers("/api/usuarios/login", "/api/usuarios/registro").permitAll()
                        .requestMatchers("/api/Domicilio/list", "/api/Domicilio/encamino", "/api/Domicilio/entregado")
                        .hasAnyRole(RolEnum.DOMICILIARIO.getValor(), RolEnum.ADMINISTRADOR.getValor())
                        .requestMatchers("/api/Restaurante/pedidos", "/api/Restaurante/saveitems", "/api/Restaurante/savepedido")
                        .hasAnyRole(RolEnum.RESTAURANTE.getValor(), RolEnum.ADMINISTRADOR.getValor())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}

