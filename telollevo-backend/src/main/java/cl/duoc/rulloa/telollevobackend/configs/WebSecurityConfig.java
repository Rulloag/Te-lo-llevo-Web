package cl.duoc.rulloa.telollevobackend.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final CorsConfigurationSource corsConfigurationSource;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    var permitAll = new String[] {"/**"};
    http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            .authorizeHttpRequests(r -> r.requestMatchers(permitAll).permitAll().anyRequest().authenticated())
            .csrf(AbstractHttpConfigurer::disable)
            .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource));
    return http.build();
  }
}
