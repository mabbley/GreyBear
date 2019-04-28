package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by mby on 2018-8-10.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_groups")
public class RoleGroups extends MyBatisEntity<Long> {

    /**
     *  角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     *  组ID
     */
    @TableField("group_id")
    private Long groupId;

    public static RoleGroups create(Long roleId, Long groupId){
        RoleGroups roleMenu = new RoleGroups();
        roleMenu.setRoleId(roleId);
        roleMenu.setGroupId(groupId);
        roleMenu.setDel(0);
        roleMenu.setCreateDate(new Date());
        roleMenu.setUpdateDate(roleMenu.getCreateDate());
        return roleMenu;
    }
}
