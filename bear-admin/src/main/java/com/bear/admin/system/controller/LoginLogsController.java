package com.bear.admin.system.controller;

import com.bear.admin.common.annotation.ControllerModel;
import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.LoginLogs;
import com.bear.admin.system.service.LoginLogsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mby on 2019/4/26.
 */
@Controller
@RequestMapping("/sys/loginLogs")
@ControllerModel(modelName = "登录日志",viewPath = "system/logs/login")
public class LoginLogsController extends MyBatisController<LoginLogs,Long, LoginLogsService> {
}
