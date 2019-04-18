package com.bear.admin.common.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * Created by mby on 2019/4/17.
 */
public interface MyBatisMapper<T,ID extends Serializable> extends BaseMapper<T> {
}
