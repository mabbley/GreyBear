package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.Organization;
import com.bear.admin.system.mapper.OrganizationMapper;
import com.bear.admin.system.service.OrganizationService;
import org.springframework.stereotype.Service;


/**
* (SysOrganization)表服务实现类
*
* @author mby
* @version 1.0
* @since 2019/4/19 10:0
*/
@Service
public class OrganizationServiceImpl extends MyBatisServiceImpl<Organization,Long, OrganizationMapper> implements OrganizationService {

}