package com.bear.admin.system.mapper;

import com.bear.admin.system.entity.Role;
import org.springframework.stereotype.Repository;
import com.bear.admin.common.base.mapper.MyBatisMapper;
/**
* (SysRole)表Mapper类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Repository
public interface RoleMapper extends MyBatisMapper<Role,Long> {

}