package com.bear.admin.common.base.controller;

import com.bear.admin.common.base.entity.MyBatisEntity;
import com.bear.admin.common.base.entity.ResultView;
import com.bear.admin.common.base.entity.SearchParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mby on 2019/4/17.
 */
public interface BaseMyBatisController<T,ID extends Serializable> {

    /**
     * 分页查询
     * @param searchParam
     * @return
     */
    @RequestMapping(value = "findPage", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    ResultView<T> findPage(@NotNull(message = "分页查询对象不能为空")SearchParam searchParam);

    /**
     * 新增
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    ResultView<T> create(@NotNull(message = "对象不能为空")T t);

    /**
     * 修改
     * @param t
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    ResultView<T> update(@NotNull(message = "被修改对象不能为空")T t);

    /**
     * 根据条件修改
     * @param t
     * @param param
     * @return
     */
    @RequestMapping(value = "updateParam", method = RequestMethod.POST)
    @ResponseBody
    ResultView<T> updateParam(@NotNull(message = "被修改对象不能为空")T t,@NotNull(message = "条件param不能为空")Map<String, Object> param);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "findById/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ResultView<T> findById(@NotNull(message = "id 不能为空")@PathVariable("id") ID id);

    /**
     * 多条件查询
     * @param param
     * @return
     */
    @RequestMapping(value = "findByParam", method = RequestMethod.POST)
    @ResponseBody
    ResultView<List<T>> findByParam(@NotNull(message = "param 不能为空")Map<String, Object> param);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteById/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ResultView<T> deleteById(@NotNull(message = "id 不能为空")@PathVariable("id")ID id);

    /**
     * 根据ID集合批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
    @ResponseBody
    ResultView<T> deleteByIds(@NotEmpty(message = "ID集合不能为空") List<ID> ids);
}
