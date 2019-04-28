package com.bear.admin.system.service.impl;

import com.bear.admin.common.base.service.impl.MyBatisServiceImpl;
import com.bear.admin.system.entity.Groups;
import com.bear.admin.system.mapper.GroupsMapper;
import com.bear.admin.system.service.GroupsService;
import org.springframework.stereotype.Service;

/**
 * Created by mby on 2019/4/26.
 */
@Service
public class GroupsServiceImpl extends MyBatisServiceImpl<Groups,Long, GroupsMapper>  implements GroupsService {
}
