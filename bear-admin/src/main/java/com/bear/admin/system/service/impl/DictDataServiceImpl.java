package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.DictData;
import com.bear.admin.system.mapper.DictDataMapper;
import com.bear.admin.system.service.DictDataService;
import org.springframework.stereotype.Service;


/**
* (SysDictData)表服务实现类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Service
public class DictDataServiceImpl extends MyBatisServiceImpl<DictData,Long, DictDataMapper> implements DictDataService {

}