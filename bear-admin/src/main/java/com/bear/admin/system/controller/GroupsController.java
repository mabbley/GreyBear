package com.bear.admin.system.controller;

import com.bear.admin.common.annotation.ControllerModel;
import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.Groups;
import com.bear.admin.system.service.GroupsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mby on 2019/4/26.
 */
@Controller
@RequestMapping("/sys/groups")
@ControllerModel(modelName = "组",viewPath = "system/groups")
public class GroupsController extends MyBatisController<Groups,Long, GroupsService> {
}
