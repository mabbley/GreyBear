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
@TableName("sys_org_menu")
public class OrgMenu extends MyBatisEntity<Long> {

    /**
     *  机构ID
     */
    @TableField("org_id")
    private Long orgId;

    /**
     *  菜单ID
     */
    @TableField("menu_id")
    private Long menuId;

    public static OrgMenu create(Long orgId, Long menuId){
        OrgMenu roleMenu = new OrgMenu();
        roleMenu.setOrgId(orgId);
        roleMenu.setMenuId(menuId);
        roleMenu.setDel(0);
        roleMenu.setCreateDate(new Date());
        roleMenu.setUpdateDate(roleMenu.getCreateDate());
        return roleMenu;
    }
}
