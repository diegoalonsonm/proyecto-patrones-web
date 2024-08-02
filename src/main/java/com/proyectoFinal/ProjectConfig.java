package com.proyectoFinal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/login", "/registro/nuevo", "/destino/listadoPublico",
                        "/errores/**","/sedes/nuestrasSedes", "/js/**", "/webjars/**", "/styles/**").permitAll()
                .requestMatchers("/destino/agregar", "/destino/listado", "/destino/eliminar/**",
                        "/destino/editar/**", "paquete/agregar", "/paquete/listado", "/paquete/eliminar/**", "/paquete/editar/**").hasRole("ADMIN")
                .requestMatchers("/paquete/listado", "/destino/listado").hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers("/facturar/carrito").hasRole("USER"))
                .formLogin((form) -> form
                        .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

}