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
@TableName("sys_admin_menu")
public class AdminMenu extends MyBatisEntity<Long> {

    /**
     *  管理员ID
     */
    @TableField("admin_id")
    private Long adminId;

    /**
     *  菜单ID
     */
    @TableField("menu_id")
    private Long menuId;

    public static AdminMenu create(Long adminId, Long menuId){
        AdminMenu roleMenu = new AdminMenu();
        roleMenu.setAdminId(adminId);
        roleMenu.setMenuId(menuId);
        roleMenu.setDel(0);
        roleMenu.setCreateDate(new Date());
        roleMenu.setUpdateDate(roleMenu.getCreateDate());
        return roleMenu;
    }
}
