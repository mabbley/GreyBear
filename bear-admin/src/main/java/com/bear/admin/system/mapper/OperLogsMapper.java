package com.bear.admin.system.mapper;

import com.bear.admin.common.base.mapper.MyBatisMapper;
import com.bear.admin.system.entity.OperLogs;
import org.springframework.stereotype.Repository;

/**
 * Created by mby on 2019/4/18.
 */
@Repository
public interface OperLogsMapper extends MyBatisMapper<OperLogs,Long> {
}
