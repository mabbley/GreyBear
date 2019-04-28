package com.bear.admin.system.controller;

import com.bear.admin.common.annotation.ControllerModel;
import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.OperLogs;
import com.bear.admin.system.service.OperLogsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mby on 2019/4/26.
 */
@Controller
@RequestMapping("/sys/operLogs")
@ControllerModel(modelName = "操作日志",viewPath = "system/logs/oper")
public class OperLogsController extends MyBatisController<OperLogs,Long, OperLogsService> {
}
