package com.bear.admin.common.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bear.admin.common.base.entity.MyBatisEntity;
import com.bear.admin.common.base.entity.SearchParam;

import java.io.Serializable;

/**
 * Created by mby on 2019/4/17.
 */
public interface MyBatisService<T extends MyBatisEntity<ID>,ID extends Serializable> extends IService<T> {
    IPage<T> findPage(SearchParam searchParam);
}
