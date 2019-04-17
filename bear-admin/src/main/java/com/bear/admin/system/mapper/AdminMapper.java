package com.bear.admin.system.mapper;

import com.bear.admin.common.base.mapper.MyBatisMapper;
import com.bear.admin.system.entity.Admin;
import org.springframework.stereotype.Repository;

/**
 * Created by mby on 2019/4/17.
 */
@Repository
public interface AdminMapper extends MyBatisMapper<Admin,Long> {
}
