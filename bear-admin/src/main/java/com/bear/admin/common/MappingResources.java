package com.bear.admin.common;

import com.alibaba.fastjson.JSON;
import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.service.MenuService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mby on 2019/4/28.
 */
@Slf4j
//@Component
public class MappingResources {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private MenuService menuService;

    @PostConstruct
    public void resources(){
        int sort = 1;
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        List<Menu> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Menu menu = new Menu();
            menu.setWeight(sort);
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            if(null != p){
                ArrayList<String> strings = Lists.newArrayList(p.getPatterns());
                if(null != strings && strings.size()>0){
                    menu.setUrl(strings.get(0));
                }
            }
            menu.setClassPackageName(method.getBeanType().getName());
            menu.setClassName(method.getBeanType().getSimpleName());
            menu.setMethod(method.getMethod().getName());
            menu.setStatus(0);
            list.add(menu);
            sort++;
        }
        log.info("-------------------------------{}", JSON.toJSONString(list));
        menuService.saveBatch(list);
    }

}
