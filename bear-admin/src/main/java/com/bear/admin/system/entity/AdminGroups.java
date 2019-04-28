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
@TableName("sys_admin_groups")
public class AdminGroups extends MyBatisEntity<Long> {

    /**
     *  管理员ID
     */
    @TableField("admin_id")
    private Long adminId;

    /**
     *  组ID
     */
    @TableField("group_id")
    private Long groupId;

    public static AdminGroups create(Long adminId, Long groupId){
        AdminGroups adminRole = new AdminGroups();
        adminRole.setAdminId(adminId);
        adminRole.setGroupId(groupId);
        adminRole.setDel(0);
        adminRole.setCreateDate(new Date());
        adminRole.setUpdateDate(adminRole.getCreateDate());
        return adminRole;
    }
}
