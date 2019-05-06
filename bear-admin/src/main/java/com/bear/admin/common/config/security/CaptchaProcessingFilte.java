package com.bear.admin.common.config.security;

import com.bear.common.core.exception.CaptchaException;
import com.google.code.kaptcha.Constants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mby on 2019/4/28.
 */

public class CaptchaProcessingFilte extends AbstractAuthenticationProcessingFilter {

    protected CaptchaProcessingFilte() {
        super(new AntPathRequestMatcher("/login", "POST"));
        setContinueChainBeforeSuccessfulAuthentication(true);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String validCode = httpServletRequest.getParameter("validateCode");
        Object attribute = httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!validCode.trim().equals(attribute.toString().trim())){
            throw new CaptchaException(-100,"验证码输入有误");
        }
        String username = httpServletRequest.getParameter("loginUser");
        String password = httpServletRequest.getParameter("loginPwd");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(token);
    }


    public SimpleUrlAuthenticationFailureHandler getFailureHandler() {
        SimpleUrlAuthenticationFailureHandler failureHandler = (SimpleUrlAuthenticationFailureHandler) super.getFailureHandler();
        //本句也必须设置为true，否则验证失败后,不会转至MySecurityConfig类中配置的failureUrl方法中
        failureHandler.setUseForward(true);
        return failureHandler;
    }
}
