package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 机构
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_organization")
public class Organization extends MyBatisEntity<Long> {

    /**
     *  部门名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     *  上级部门ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     *  负责人
     */
    private String leader;

    /**
     *  状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;
}
