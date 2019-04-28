package com.bear.admin.system.mapper;

import com.bear.admin.common.base.mapper.MyBatisMapper;
import com.bear.admin.system.entity.AdminMenu;
import org.springframework.stereotype.Repository;

/**
 * Created by mby on 2019/4/26.
 */
@Repository
public interface AdminMenuMapper extends MyBatisMapper<AdminMenu,Long> {
}
