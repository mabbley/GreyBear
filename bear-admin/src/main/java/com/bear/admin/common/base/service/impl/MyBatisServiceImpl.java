package com.bear.admin.common.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bear.admin.common.base.entity.SearchParam;
import com.bear.admin.common.base.mapper.MyBatisMapper;
import com.bear.admin.common.base.service.MyBatisService;
import com.bear.common.core.ReflectionUtils;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by mby on 2019/4/17.
 */
@SuppressWarnings("Duplicates")
public class MyBatisServiceImpl<T,ID extends Serializable,BaseMapper extends MyBatisMapper<T,ID>> extends ServiceImpl<BaseMapper,T> implements MyBatisService<T,ID> {

    @Override
    protected Class<T> currentModelClass() {
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }

    private QueryWrapper<T> getQueryWrapper(Map<String, Object> paramMap){
        QueryWrapper<T> entity = new QueryWrapper<>();
        if(null != paramMap && !paramMap.isEmpty()) {
            for (Map.Entry<String, Object> param : paramMap.entrySet()) {
                String[] split = param.getKey().split("@");
                switch (split[1]) {
                    case "eq": {
                        entity.eq(split[0], param.getValue());
                        break;
                    }
                    case "gt": {
                        entity.gt(split[0], param.getValue());
                        break;
                    }
                    case "get": {
                        entity.ge(split[0], param.getValue());
                        break;
                    }
                    case "lt": {
                        entity.lt(split[0], param.getValue());
                        break;
                    }
                    case "let": {
                        entity.le(split[0], param.getValue());
                        break;
                    }
                    case "like": {
                        entity.like(split[0], param.getValue().toString());
                        break;
                    }
                    case "likeL": {
                        entity.likeLeft(split[0], param.getValue().toString());
                        break;
                    }
                    case "likeR": {
                        entity.likeRight(split[0], param.getValue().toString());
                        break;
                    }
                    case "notLike": {
                        entity.notLike(split[0], param.getValue().toString());
                        break;
                    }
                    case "in": {
                        if (param.getValue() instanceof String) {
                            entity.in(split[0], param.getValue().toString());
                        } else if (param.getValue() instanceof Collection) {
                            entity.in(split[0], (Collection<?>) param.getValue());
                        } else {
                            entity.in(split[0], (Object[]) param.getValue());
                        }
                        break;
                    }
                    case "ne": {
                        entity.ne(split[0], param.getValue());
                        break;
                    }
                }
            }
        }
        return entity;
    }

    private UpdateWrapper<T> getUpdateWrapper(Map<String, Object> paramMap){
        UpdateWrapper<T> entity = new UpdateWrapper<>();
        if(null != paramMap && !paramMap.isEmpty()) {
            for (Map.Entry<String, Object> param : paramMap.entrySet()) {
                String[] split = param.getKey().split("@");
                switch (split[1]) {
                    case "eq": {
                        entity.eq(split[0], param.getValue());
                        break;
                    }
                    case "gt": {
                        entity.gt(split[0], param.getValue());
                        break;
                    }
                    case "get": {
                        entity.ge(split[0], param.getValue());
                        break;
                    }
                    case "lt": {
                        entity.lt(split[0], param.getValue());
                        break;
                    }
                    case "let": {
                        entity.le(split[0], param.getValue());
                        break;
                    }
                    case "like": {
                        entity.like(split[0], param.getValue().toString());
                        break;
                    }
                    case "likeL": {
                        entity.likeLeft(split[0], param.getValue().toString());
                        break;
                    }
                    case "likeR": {
                        entity.likeRight(split[0], param.getValue().toString());
                        break;
                    }
                    case "notLike": {
                        entity.notLike(split[0], param.getValue().toString());
                        break;
                    }
                    case "in": {
                        if (param.getValue() instanceof String) {
                            entity.in(split[0], param.getValue().toString());
                        } else if (param.getValue() instanceof Collection) {
                            entity.in(split[0], (Collection<?>) param.getValue());
                        } else {
                            entity.in(split[0], (Object[]) param.getValue());
                        }
                        break;
                    }
                    case "ne": {
                        entity.ne(split[0], param.getValue());
                        break;
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public IPage<T> findPage(SearchParam searchParam) {
        QueryWrapper<T> entity = getQueryWrapper(searchParam.getParam());
        return baseMapper.selectPage(searchParam.toPageParam(),entity);
    }

    @Override
    public List<T> findByParam(Map<String, Object> searchParam) {
        if(null != searchParam){
            return baseMapper.selectList(getQueryWrapper(searchParam));
        }
        return Lists.newArrayList();
    }

    @Override
    public Integer updateParam(T t, Map<String, Object> param) {
        return baseMapper.update(t,getUpdateWrapper(param));
    }
}
