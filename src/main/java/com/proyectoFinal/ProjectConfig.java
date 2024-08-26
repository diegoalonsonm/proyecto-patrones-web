package com.proyectoFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Locale;


@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public LocaleContextResolver localResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");

        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");

        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

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
                        .requestMatchers("/", "/index", "/errores/**", "/carrito/**", "/js/**", "/styles/**", "/webjars/**",
                                "/destino/listadoPublico", "/destino/buscarPorTiempo", "/otros/**", "/paquete/listadoPublico",
                                "/paquete/buscarPorPrecio", "/usuario/registroNuevo", "/usuario/registro", "/hotel/listadoPublico",
                                "/hotel/buscarPorEstrellas").permitAll()
                        .requestMatchers("/destino/agregar", "/destino/agregarDestino", "/destino/eliminar/**", "/destino/editar/**",
                                "/paquete/agregar", "/paquete/agregarPaquete", "/paquete/eliminar/**", "/paquete/editar/**", "/usuario/listado",
                                "/usuario/modifica/**", "/usuario/agregar", "/usuario/guardar", "/usuario/eliminar/**",
                                "usuario/modificar/**").hasRole("ADMIN")
                        .requestMatchers("/destino/listado", "/paquete/listado", "/hotel/listado").hasAnyRole("ADMIN", "VENDEDOR")
                        .requestMatchers("/facturar/**", "/reserva/misReservas/**", "/reserva/reservaDestino/**", "reserva/reservaPaquete/**",
                                "/reserva/reservarDestino/**", "/reserva/reservarPaquete/**", "/reserva/cancelarReservaDestino/**",
                                "/reserva/cancelarReservaPaquete/**", "/hotel/agregar", "/hotel/editar/**", "/hotel/agregarHotel/**",
                                "/reserva/reservaHotel/**", "/reserva/reservarHotel", "/reserva/cancelarReservaHotel/**").hasRole("USER"))
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/index").permitAll());
        return http.build();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver_0() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setOrder(0);
        resolver.setCheckExistence(true);
        return resolver;
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}