package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.mapper.AdminMapper;
import com.bear.admin.system.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * Created by mby on 2019/4/17.
 */
@Service
public class AdminServiceImpl extends MyBatisServiceImpl<Admin,Long, AdminMapper> implements AdminService {
}
