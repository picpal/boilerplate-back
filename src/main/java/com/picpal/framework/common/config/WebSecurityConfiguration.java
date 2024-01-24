package com.picpal.framework.common.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  {
    private static final String[] WHITE_LIST = {};

    @Bean
    protected SecurityFilterChain filterChainConfigure(HttpSecurity http) throws Exception{
        http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .securityMatcher("/**")
            .formLogin(AbstractHttpConfigurer::disable)
            .sessionManagement(
                    sessionManagementConfigure -> sessionManagementConfigure
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ) // 세션 유지X ( rest API는 stateless )
            .authorizeHttpRequests(
                    request -> request
                            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                            .requestMatchers(WHITE_LIST).permitAll() // 인증 없이 접근 가능한 endpoint
//                            .requestMatchers("/v1/..").hasRole("admin") // admin 권한만 접근 가능한 endpoint
//                            .requestMatchers("/v1/..").hasRole("user") // user 권한만 접근 가능한 endpoint
                            .anyRequest()
                            .authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 단방향 암호화 , 같은 값이라도 매번 다른 값 반환
        return new BCryptPasswordEncoder();

        // 단방향 SHA512 암호화
        // return new EncSHA512Password();
    }
}