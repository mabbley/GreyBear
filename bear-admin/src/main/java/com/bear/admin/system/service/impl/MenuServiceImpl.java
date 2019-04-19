package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.mapper.MenuMapper;
import com.bear.admin.system.service.MenuService;
import org.springframework.stereotype.Service;


/**
* (SysMenu)表服务实现类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Service
public class MenuServiceImpl extends MyBatisServiceImpl<Menu,Long, MenuMapper> implements MenuService {

}