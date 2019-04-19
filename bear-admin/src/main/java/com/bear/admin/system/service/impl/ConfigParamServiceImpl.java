package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.ConfigParam;
import com.bear.admin.system.mapper.ConfigParamMapper;
import com.bear.admin.system.service.ConfigParamService;
import org.springframework.stereotype.Service;


/**
* (SysConfigParam)表服务实现类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Service
public class ConfigParamServiceImpl extends MyBatisServiceImpl<ConfigParam,Long, ConfigParamMapper> implements ConfigParamService {

}