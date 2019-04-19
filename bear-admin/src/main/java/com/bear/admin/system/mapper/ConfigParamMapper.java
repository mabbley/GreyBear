package com.bear.admin.system.mapper;

import com.bear.admin.system.entity.ConfigParam;
import org.springframework.stereotype.Repository;
import com.bear.admin.common.base.mapper.MyBatisMapper;
/**
* (SysConfigParam)表Mapper类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Repository
public interface ConfigParamMapper extends MyBatisMapper<ConfigParam,Long> {

}