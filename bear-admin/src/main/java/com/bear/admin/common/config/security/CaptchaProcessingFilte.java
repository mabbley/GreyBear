package com.bear.admin.common.config.security;

import com.google.code.kaptcha.Constants;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mby on 2019/4/28.
 */

public class CaptchaProcessingFilte extends UsernamePasswordAuthenticationFilter {

    public CaptchaProcessingFilte(){
        setUsernameParameter("loginUser");
        setPasswordParameter("loginPwd");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException{
        String validCode = httpServletRequest.getParameter("validateCode");
        Object attribute = httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!validCode.trim().equals(attribute.toString().trim())){
            throw new AccessDeniedException("验证码错误");
        }
        String username = obtainUsername(httpServletRequest);
        String password = obtainPassword(httpServletRequest);
        Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(httpServletRequest, (AbstractAuthenticationToken)authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
