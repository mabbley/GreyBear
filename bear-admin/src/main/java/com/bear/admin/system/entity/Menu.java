package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import com.bear.admin.common.enums.MenuType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu extends MyBatisEntity<Long> {

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * icon图标
     */
    @TableField("icon")
    private String icon;

    /**
     * URL
     */
    @TableField("url")
    private String url;

    /**
     * 类型{ML("目录"),MENU("菜单"),BUTTON("按钮")}
     */
    @TableField("menu_type")
    private MenuType menuType;

    /**
     * 状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;

    /**
     * 排序权重
     */
    @TableField("weight")
    private Integer weight;

    /**
     * 简述
     */
    @TableField("synopsis")
    private String synopsis;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();
}
