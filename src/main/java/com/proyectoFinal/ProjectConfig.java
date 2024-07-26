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
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    //@Bean
    //public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //    http.authorizeHttpRequests((request) -> request
    //            .requestMatchers("/", "/index", "/errores/**",
    //                    "/pruebas/**", "/registro/**", "/js/**", "/webjars/**")
    //            .permitAll()
    //            .requestMatchers(
    //                    "/vuelos/nuevo", "/vuelos/guardar",
    //                    "vuelos/modificar/**", "vuelos/eliminar/**"
    //           )
    //    )
    //}

}