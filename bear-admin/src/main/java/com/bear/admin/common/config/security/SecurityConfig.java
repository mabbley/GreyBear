package com.bear.admin.common.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.annotation.Resource;

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
    private AuthenticationManager authenticationManager;


    @Autowired
    private CustomizeAccessDecisionManager customizeAccessDecisionManager;

    @Autowired
    private CustomizeFilterInvocationSecurityMetadataSource customizeFilterInvocationSecurityMetadataSource;



    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CsrfSecurityRequestMatcher csrfSecurityRequestMatcher(){
        return new CsrfSecurityRequestMatcher();
    }

    @Bean
    public CaptchaProcessingFilte CaptchaProcessingFilte(){
        CaptchaProcessingFilte captchaProcessingFilte = new CaptchaProcessingFilte();
        captchaProcessingFilte.setAuthenticationManager(authenticationManager);
        return captchaProcessingFilte;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**","/static/**","/css/**","/fonts/**","/img/**","/js/**","/libs/**","/my/**","/index","/captcha/**");
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().
                requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())//.and().addFilterBefore(new CustomizeSecurityFilter(customizeAccessDecisionManager,customizeFilterInvocationSecurityMetadataSource),UsernamePasswordAuthenticationFilter.class).authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login","/resources/**","/static/**","/css/**","/fonts/**","/img/**","/js/**","/libs/**","/my/**","/index","/captcha/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("loginUser")
                .passwordParameter("loginPwd")
                .loginPage("/login")
                .permitAll()
                .successHandler(customizeAuthenticationSuccessHandler)
                .failureHandler(customizeAuthenticationFailHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customizaAccessDeniedHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe().rememberMeParameter("remember-me")
                .tokenValiditySeconds(1209600)
//                .tokenRepository(tokenRepository)
                .and()
                .sessionManagement()
                .maximumSessions(1).expiredUrl("/login?error=true").maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry());// code4
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository =
                new HttpSessionSecurityContextRepository();
        return httpSessionSecurityContextRepository;
    }
}
