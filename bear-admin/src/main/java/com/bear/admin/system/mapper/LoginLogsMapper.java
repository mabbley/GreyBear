package com.bear.admin.system.mapper;

import com.bear.admin.common.base.mapper.MyBatisMapper;
import com.bear.admin.system.entity.LoginLogs;
import org.springframework.stereotype.Repository;

/**
 * Created by mby on 2019/4/18.
 */
@Repository
public interface LoginLogsMapper extends MyBatisMapper<LoginLogs,Long> {
}
