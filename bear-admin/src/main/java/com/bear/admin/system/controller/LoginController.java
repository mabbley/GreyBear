package com.bear.admin.system.controller;

import com.bear.admin.common.base.entity.ResultView;
import com.bear.admin.common.config.shiro.ShiroUtils;
import com.bear.common.core.IpUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.shiro.authc.*;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mby on 2019/4/24.
 */
@Controller
public class LoginController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, Long> loginPwdCountCache;

    @PostConstruct
    public void init() {
        loginPwdCountCache = cacheManager.getCache("loginPwdCountCache");
    }

    /**
     * 验证码生成
     */
    @GetMapping(value = "/captcha/captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");
            String type = request.getParameter("type");
            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(type)) {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            } else if ("char".equals(type)) {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public ResultView login(String loginUser, String loginPwd, String validateCode, Boolean rememberMe) {
        Long atomicInteger = loginPwdCountCache.get(loginUser);
        if(null != atomicInteger){
            Long temp = System.currentTimeMillis() - atomicInteger;
            if(temp<(1000*60*10)){
                return ResultView.result(-200,"账号或密码错误次数过多,请十分钟后再试1",null);
            }else{
                loginPwdCountCache.remove(loginUser);
            }
        }
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!validateCode.equalsIgnoreCase(kaptcha)){
            return ResultView.result(-200,"验证码不正确",null);
        }
        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginUser, loginPwd,rememberMe);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return ResultView.result(-200,e.getMessage(),null);
        }catch (IncorrectCredentialsException e) {
            return ResultView.result(-200,"账号或密码不正确",null);
        }catch (LockedAccountException e) {
            return ResultView.result(-200,"账号已被锁定,请联系管理员",null);
        }catch (AuthenticationException e) {
            return ResultView.result(-200,"账户验证失败",null);
        }
        return ResultView.result(true);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login";
    }
}
