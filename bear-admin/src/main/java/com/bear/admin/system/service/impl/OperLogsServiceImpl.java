package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.OperLogs;
import com.bear.admin.system.mapper.OperLogsMapper;
import com.bear.admin.system.service.OperLogsService;
import org.springframework.stereotype.Service;

/**
 * Created by mby on 2019/4/26.
 */
@Service
public class OperLogsServiceImpl extends MyBatisServiceImpl<OperLogs,Long, OperLogsMapper> implements OperLogsService {
}
