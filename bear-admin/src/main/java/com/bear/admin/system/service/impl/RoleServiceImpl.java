package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.Role;
import com.bear.admin.system.mapper.RoleMapper;
import com.bear.admin.system.service.RoleService;
import org.springframework.stereotype.Service;


/**
* (SysRole)表服务实现类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Service
public class RoleServiceImpl extends MyBatisServiceImpl<Role,Long, RoleMapper> implements RoleService {

}