package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.LoginLogs;
import com.bear.admin.system.mapper.LoginLogsMapper;
import com.bear.admin.system.service.LoginLogsService;
import org.springframework.stereotype.Service;

/**
 * Created by mby on 2019/4/26.
 */
@Service
public class LoginLogsServiceImpl extends MyBatisServiceImpl<LoginLogs,Long, LoginLogsMapper> implements LoginLogsService {
}
