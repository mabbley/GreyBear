package com.bear.admin.common.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mby on 2019/4/28.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)//prePostEnabled使@PreAuthorize生效
@Order(-1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "customizeUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private CustomizeAuthenticationFailHandler customizeAuthenticationFailHandler;

    @Autowired
    private CustomizaAccessDeniedHandler customizaAccessDeniedHandler;

    @Autowired
    private CustomizeFilterInvocationSecurityMetadataSource customizeFilterInvocationSecurityMetadataSource;

    @Autowired
    private CustomizeAccessDecisionManager customizeAccessDecisionManager;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(customizeFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(customizeAccessDecisionManager);
                        return o;
                    }
                }).and().
                formLogin().
                loginPage("/login").
                usernameParameter("loginUser").
                passwordParameter("loginPwd").
                permitAll().
                failureHandler(customizeAuthenticationFailHandler).
                successHandler(customizeAuthenticationSuccessHandler).
                and().
                logout().
                permitAll().
                and().
                csrf().
                disable().
                exceptionHandling().
                accessDeniedHandler(customizaAccessDeniedHandler);
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        //设置对spring security的UserDetails进行session保存,这个必须要有，不然不会保存至session对应的缓存redis中
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository =
                new HttpSessionSecurityContextRepository();
        return httpSessionSecurityContextRepository;
    }
}
