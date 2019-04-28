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
@TableName("sys_menu_groups")
public class MenuGroup extends MyBatisEntity<Long> {

    /**
     *  权限ID
     */
    @TableField("menu_id")
    private Long menuId;

    /**
     *  组ID
     */
    @TableField("group_id")
    private Long groupId;

    public static MenuGroup create(Long menuId, Long groupId){
        MenuGroup adminRole = new MenuGroup();
        adminRole.setMenuId(menuId);
        adminRole.setGroupId(groupId);
        adminRole.setDel(0);
        adminRole.setCreateDate(new Date());
        adminRole.setUpdateDate(adminRole.getCreateDate());
        return adminRole;
    }
}
