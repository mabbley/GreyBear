package com.bear.admin.common.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bear.admin.common.base.entity.MyBatisEntity;
import com.bear.admin.common.base.entity.SearchParam;
import com.bear.admin.common.base.mapper.MyBatisMapper;
import com.bear.admin.common.base.service.MyBatisService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by mby on 2019/4/17.
 */
public class MyBatisServiceImpl<T extends MyBatisEntity<ID>,ID extends Serializable,BaseMapper extends MyBatisMapper<T,ID>> extends ServiceImpl<BaseMapper,T> implements MyBatisService<T,ID> {

    @Override
    public IPage<T> findPage(SearchParam searchParam) {
        Map<String, Object> paramMap = searchParam.getParam();
        QueryWrapper<T> entity = new QueryWrapper<>();
        if(null != paramMap && !paramMap.isEmpty()){
            for (Map.Entry<String, Object> param : paramMap.entrySet()){
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
            return baseMapper.selectPage(searchParam.toPageParam(),entity);
        }
        return baseMapper.selectPage(searchParam.toPageParam(),entity);
    }
}
