package com.bear.admin.system.controller;

import com.bear.admin.common.annotation.ControllerModel;
import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.ConfigParam;
import com.bear.admin.system.service.ConfigParamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
* (SysConfigParam)
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Controller
@RequestMapping("/sys/configParam")
@ControllerModel(modelName = "系统参数",viewPath = "system/param")
public class ConfigParamController extends MyBatisController<ConfigParam,Long, ConfigParamService>  {

}