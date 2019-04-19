package com.bear.admin.system.mapper;

import com.bear.admin.system.entity.Menu;
import org.springframework.stereotype.Repository;
import com.bear.admin.common.base.mapper.MyBatisMapper;
/**
* (SysMenu)表Mapper类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Repository
public interface MenuMapper extends MyBatisMapper<Menu,Long> {

}