package com.bear.admin.common.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mby on 2019/4/28.
 */
public class CaptchaProcessingFilte extends AbstractAuthenticationProcessingFilter {

    protected CaptchaProcessingFilte(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setContinueChainBeforeSuccessfulAuthentication(true);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String username = httpServletRequest.getParameter("loginUser");
        String password = httpServletRequest.getParameter("loginPwd");
        String validCode = httpServletRequest.getParameter("validateCode");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return token;
    }


    public SimpleUrlAuthenticationFailureHandler getFailureHandler() {
        SimpleUrlAuthenticationFailureHandler failureHandler = (SimpleUrlAuthenticationFailureHandler) super.getFailureHandler();
        //本句也必须设置为true，否则验证失败后,不会转至MySecurityConfig类中配置的failureUrl方法中
        failureHandler.setUseForward(true);
        return failureHandler;
    }
}
