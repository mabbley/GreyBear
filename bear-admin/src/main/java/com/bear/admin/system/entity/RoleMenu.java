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
@TableName("sys_role_menu")
public class RoleMenu extends MyBatisEntity<Long> {

    /**
     *  角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     *  菜单ID
     */
    @TableField("menu_id")
    private Long menuId;

    public static RoleMenu create(Long roleId,Long menuId){
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        roleMenu.setDel(0);
        roleMenu.setCreateDate(new Date());
        roleMenu.setUpdateDate(roleMenu.getCreateDate());
        return roleMenu;
    }
}
