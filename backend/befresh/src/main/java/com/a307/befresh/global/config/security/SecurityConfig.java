package com.a307.befresh.global.config.security;

import com.a307.befresh.global.security.filter.JsonMemberAuthenticationFilter;
import com.a307.befresh.global.security.filter.JwtAuthenticationProcessingFilter;
import com.a307.befresh.global.security.handler.LoginFailureHandler;
import com.a307.befresh.global.security.handler.LoginSuccessJWTProvideHandler;
import com.a307.befresh.global.security.jwt.JwtService;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/health", "/api/login", "/api/member/signup").permitAll()
                .anyRequest().authenticated())
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        http
            .addFilterAfter(jsonMemberAuthenticationFilter(),
                LogoutFilter.class)
            .addFilterBefore(jwtAuthenticationProcessingFilter(),
                JsonMemberAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(
            List.of("http://localhost:5173", "https://be-fresh.site"));
        configuration.setAllowedMethods(
            Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "OPTION"));     // 허용할 HTTP 메소드
        configuration.setAllowedHeaders(Arrays.asList(      // 요청 헤더 중 허용할 헤더 설정
            "Authorization",
            "Cache-Control",
            "Content-Type"
        ));
        configuration.setAllowCredentials(true);    // 인증 정보(cookies, headers) 등을 포함한 요청을 허용하도록 설정
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization",
            "Authorization-refresh"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();     // URL 기반의 CORS 설정 소스 객체 생성
        source.registerCorsConfiguration("/**", configuration);     // 모든 url 패턴에 대해 CORS 설정 적용

        return source;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
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
        JsonMemberAuthenticationFilter jsonMemberAuthenticationFilter = new JsonMemberAuthenticationFilter(
            objectMapper);
        jsonMemberAuthenticationFilter.setAuthenticationManager(authenticationManager());
        jsonMemberAuthenticationFilter.setAuthenticationSuccessHandler(
            loginSuccessJWTProvideHandler());
        jsonMemberAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return jsonMemberAuthenticationFilter;
    }

    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler() {
        return new LoginSuccessJWTProvideHandler(jwtService, memberRepository);
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        return new JwtAuthenticationProcessingFilter(
            jwtService, memberRepository);
    }

}