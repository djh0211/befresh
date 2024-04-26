package com.a307.befresh.global.config.security;

import com.a307.befresh.global.util.JsonMemberAuthenticationFilter;
import com.a307.befresh.module.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
            .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .addFilterAfter(jsonMemberAuthenticationFilter(), LogoutFilter.class)
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/health", "/api/member/**").permitAll()
                .anyRequest().authenticated())
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:8000", "https://be-fresh.site"));
        configuration.setAllowedMethods(
            Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "OPTION"));     // 허용할 HTTP 메소드
        configuration.setAllowedHeaders(Arrays.asList(      // 요청 헤더 중 허용할 헤더 설정
            "Authorization",
            "Cache-Control",
            "Content-Type"
        ));
        configuration.setAllowCredentials(true);    // 인증 정보(cookies, headers) 등을 포함한 요청을 허용하도록 설정

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();     // URL 기반의 CORS 설정 소스 객체 생성
        source.registerCorsConfiguration("/**", configuration);     // 모든 url 패턴에 대해 CORS 설정 적용

        return source;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService((UserDetailsService) memberService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider provider = daoAuthenticationProvider();
        //PasswordEncoder로는 PasswordEncoderFactories.createDelegatingPasswordEncoder() 사용
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public JsonMemberAuthenticationFilter jsonMemberAuthenticationFilter() throws Exception {
        JsonMemberAuthenticationFilter jsonUsernamePasswordLoginFilter = new JsonMemberAuthenticationFilter(objectMapper);
        jsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        return jsonUsernamePasswordLoginFilter;
    }


}