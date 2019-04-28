package com.bear.admin.system.controller;

import com.bear.admin.common.annotation.ControllerModel;
import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.DictData;
import com.bear.admin.system.service.DictDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
* (SysDictData)
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Controller
@RequestMapping("/sys/dictData")
@ControllerModel(modelName = "数据字典",viewPath = "system/dict")
public class DictDataController extends MyBatisController<DictData,Long, DictDataService>  {

}