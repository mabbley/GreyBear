package com.bear.admin.system.controller;

import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
* (SysMenu)
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Controller
@RequestMapping("/sys/menu")
public class MenuController extends MyBatisController<Menu,Long, MenuService>  {

}