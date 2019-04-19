package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 系统参数
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_config_param")
public class ConfigParam extends MyBatisEntity<Long> {

    /**
     * 参数名称
     */
    @TableField("config_name")
    private String configName;

    /**
     * 参数键名
     */
    @TableField("config_key")
    private String configKey;

    /**
     * 参数键值
     */
    @TableField("config_value")
    private String configValue;

    /**
     * 参数备注
     */
    @TableField("config_value")
    private String configRemark;
}
