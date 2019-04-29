package com.bear.admin.system.controller;

import com.bear.admin.common.annotation.ControllerModel;
import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mby on 2019/4/18.
 */
@Slf4j
@Controller
@RequestMapping("/sys/admin")
@ControllerModel(modelName = "管理员",viewPath = "system/admin")
public class AdminController extends MyBatisController<Admin,Long, AdminService> {

}
