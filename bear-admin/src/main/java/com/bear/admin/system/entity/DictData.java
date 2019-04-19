package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 数据字典
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dict_data")
public class DictData extends MyBatisEntity<Long> {

    /**
     * 字典类型
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 字典标签
     */
    @TableField("dict_label")
    private String dictLabel;

    /**
     * 字典键值
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 字典排序
     */
    @TableField("dict_sort")
    private Long dictSort;

    /**
     * 样式属性（其他样式扩展）
     */
    @TableField("css_class")
    private String cssClass;

    /**
     * 表格字典样式
     */
    @TableField("list_class")
    private String listClass;

    /**
     * 是否默认（0是 1否）
     */
    @TableField("is_default")
    private Integer isDefault;

    /**
     * 状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
