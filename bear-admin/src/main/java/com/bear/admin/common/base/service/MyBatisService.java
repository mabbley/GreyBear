package com.bear.admin.common.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bear.admin.common.base.entity.SearchParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mby on 2019/4/17.
 */
public interface MyBatisService<T,ID extends Serializable> extends IService<T> {
    IPage<T> findPage(SearchParam searchParam);
    List<T> findByParam(Map<String, Object> param);
    Integer updateParam(T t, Map<String, Object> param);
}
