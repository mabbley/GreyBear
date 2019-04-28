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
@TableName("sys_admin_role")
public class AdminRole extends MyBatisEntity<Long> {

    /**
     *  管理员ID
     */
    @TableField("admin_id")
    private Long adminId;

    /**
     *  角色ID
     */
    @TableField("role_id")
    private Long roleId;

    public static AdminRole create(Long adminId,Long roleId){
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(adminId);
        adminRole.setRoleId(roleId);
        adminRole.setDel(0);
        adminRole.setCreateDate(new Date());
        adminRole.setUpdateDate(adminRole.getCreateDate());
        return adminRole;
    }
}
