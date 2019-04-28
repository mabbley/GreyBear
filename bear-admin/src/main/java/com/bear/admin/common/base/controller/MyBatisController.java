package com.bear.admin.common.base.controller;

import com.bear.admin.common.annotation.ControllerModel;
import com.bear.admin.common.base.entity.ResultView;
import com.bear.admin.common.base.entity.SearchParam;
import com.bear.admin.common.base.service.MyBatisService;
import com.bear.common.core.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mby on 2019/4/17.
 */
@Slf4j
public class MyBatisController<T,ID extends Serializable,BaseService extends MyBatisService<T,ID>> implements BaseMyBatisController<T,ID>{

    @Autowired
    protected BaseService baseService;


    private final Class<T> entityClass;

    public MyBatisController() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public ResultView<T> findPage(SearchParam searchParam) {
        return ResultView.page(baseService.findPage(searchParam));
    }

    @Override
    public ResultView<T> create(T t) {
        return ResultView.result(baseService.save(t));
    }

    @Override
    public ResultView<T> update(T t) {
        return ResultView.result(baseService.updateById(t));
    }

    @Override
    public ResultView<T> updateParam(T t, Map<String, Object> param) {
        return ResultView.result(baseService.updateParam(t,param));
    }

    @Override
    public ResultView<T> findById(@PathVariable("id")ID id) {
        return ResultView.result(baseService.getById(id));
    }

    @Override
    public ResultView<List<T>> findByParam(Map<String, Object> param) {
        return ResultView.result(baseService.findByParam(param));
    }

    @Override
    public ResultView<T> deleteById(@PathVariable("id")ID id) {
        return ResultView.result(baseService.removeById(id));
    }

    @Override
    public ResultView<T> deleteByIds(List<ID> list) {
        return ResultView.result(baseService.removeByIds(list));
    }

    protected ModelAndView modelAndView(String viewName) {
        return new ModelAndView(toViewPath() + "/" + viewName);
    }

    protected String toViewPath() {
        try {
            Class<?> forName = Class.forName(this.getClass().getName());
            ControllerModel annotation = forName.getAnnotation(ControllerModel.class);
            if (null != annotation) {
                return annotation.viewPath();
            }
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException :{}", e.getMessage());
        }
        return "noPage";
    }
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView toPage(ModelMap mmap) {
        return modelAndView("list");
    }

    @RequestMapping(value = "toCreate", method = {RequestMethod.GET})
    public ModelAndView toCreate(ModelMap mmap) {
        return modelAndView("create");
    }

    @RequestMapping(value = "toEdit/{id}", method = {RequestMethod.GET})
    public ModelAndView toEdit(@NotNull(message = "主鍵ID不能为空")@PathVariable("id") ID id, ModelMap mmap) {
        T model = baseService.getById(id);
        mmap.put(toLowerCaseFirstOne(model.getClass().getSimpleName()), model);
        return modelAndView("edit");
    }

    @RequestMapping(value = "toOther/{view}", method = {RequestMethod.GET})
    public ModelAndView toOther(@NotNull(message = "View不能为空")@PathVariable("view") String view, ModelMap mmap) {
        return modelAndView(view);
    }
}
