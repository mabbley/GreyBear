package com.bear.admin.system.controller;

import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mby on 2019/4/18.
 */
@Controller
@RequestMapping("/sys/admin")
public class AdminController extends MyBatisController<Admin,Long, AdminService> {
}
