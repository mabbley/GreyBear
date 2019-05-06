package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 组
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_groups")
public class Groups extends MyBatisEntity<Long> {

    @TableField("group_name")
    private String groupName;

    @TableField("parent_id")
    private Long parentId;

    /**
     * 状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;


    /**
     * 简述
     */
    @TableField("synopsis")
    private String synopsis;


    @TableField(exist = false)
    private List<Groups> children = new ArrayList<>();
}
