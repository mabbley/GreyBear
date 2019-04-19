package com.bear.admin.system.controller;

import com.bear.admin.common.base.controller.MyBatisController;
import com.bear.admin.system.entity.Organization;
import com.bear.admin.system.service.OrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
* (SysOrganization)
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Controller
@RequestMapping("/sys/organization")
public class OrganizationController extends MyBatisController<Organization,Long, OrganizationService>  {

}