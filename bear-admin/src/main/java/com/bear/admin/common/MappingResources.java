package com.bear.admin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by mby on 2019/4/28.
 */
@Slf4j
//@Component
public class MappingResources implements CommandLineRunner {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    public void exec1(){
//        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
//            HandlerMethod method = m.getValue();
//            Class<?> beanType = method.getBeanType();
//            String simpleName = method.getBeanType().getSimpleName();
//            String name = method.getMethod().getName();
//            log.info("class={},className={},methodName={}",beanType,simpleName,name);
//        }
    }

    @Override
    public void run(String... args) throws Exception {
        /*Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HandlerMethod method = m.getValue();
            Class<?> beanType = method.getBeanType();
            String simpleName = method.getBeanType().getSimpleName();
            String name = method.getMethod().getName();
            log.info("class={},className={},methodName={}",beanType,simpleName,name);
            RequiresPermissions methodAnnotation = method.getMethodAnnotation(RequiresPermissions.class);
            if(null != methodAnnotation){
                InvocationHandler h = Proxy.getInvocationHandler(methodAnnotation);
                Field hField = h.getClass().getDeclaredField("memberValues");
                hField.setAccessible(true);
                // 获取 memberValues
                Map memberValues = (Map) hField.get(h);
                log.info("");
            }

        }*/
    }
}
