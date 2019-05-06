package com.bear;

import com.bear.admin.system.entity.Groups;
import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.entity.Organization;
import com.bear.admin.system.entity.Role;
import com.bear.admin.system.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by mby on 2019/4/30.
 */
@Slf4j
public class InitDataTest extends BaseTest{

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private GroupsMapper groupsMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void init(){
        Groups groups = new Groups();
        groupsMapper.insert(groups);

        Organization organization = new Organization();
        organizationMapper.insert(organization);

        Role role = new Role();
        roleMapper.insert(role);

        for(int i=0;i<20;i++){
            Menu menu = new Menu();
            menuMapper.insert(menu);
        }

    }
}
