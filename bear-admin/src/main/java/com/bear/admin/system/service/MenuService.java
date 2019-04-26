package com.bear.admin.system.service;

import com.bear.admin.common.base.service.MyBatisService;
import com.bear.admin.system.entity.Menu;

import java.util.List;

/**
* (SysMenu)表服务类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
public interface MenuService extends MyBatisService<Menu,Long> {

    List<Menu> findByAdmin(Long adminId);
}