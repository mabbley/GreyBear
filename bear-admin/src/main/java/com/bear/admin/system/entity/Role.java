package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 角色
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class Role extends MyBatisEntity<Long> {

    /**
     *  角色Code
     */
    @TableField("role_code")
    private String roleCode;

    /**
     *  角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     *  状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;
}
