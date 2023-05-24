package my_first_big_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests(authorizationConfigurer -> {
            authorizationConfigurer.antMatchers("/admin/***").authenticated();
            authorizationConfigurer.antMatchers("/basket/***").authenticated();
            authorizationConfigurer.antMatchers("/response/***").authenticated();
            authorizationConfigurer.antMatchers("/order/***").authenticated();
            authorizationConfigurer.anyRequest().permitAll();
        });
        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/registration/login");
            httpSecurityFormLoginConfigurer.defaultSuccessUrl("/create_product/list");
        });
        http.logout();
        return http.build();
    }
}