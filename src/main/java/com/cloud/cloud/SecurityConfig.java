package com.cloud.cloud;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg", "/public/**", "/api/**", "/data-api/**"))
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/displayProfile", "/updateProfile").authenticated()
                .requestMatchers("/", "/home", "/register", "/login", "/logout", "/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(loginConfigurer -> loginConfigurer
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logoutConfigurer -> logoutConfigurer
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID") 
                .permitAll()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
