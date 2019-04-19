package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 操作日志
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_oper_log")
public class OperLogs extends MyBatisEntity<Long> {
    /**
     * 操作模块
     */
    @TableField("title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField("business_type")
    private String businessType;

    /**
     * 请求方法
     */
    @TableField("method")
    private String method;

    /**
     * 操作人员
     */
    @TableField("oper_Name")
    private String operName;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 请求url
     */
    @TableField("oper_url")
    private String operUrl;

    /**
     * 操作地址
     */
    @TableField("oper_ip")
    private String operIp;

    /**
     * 请求参数
     */
    @TableField("oper_param")
    private String operParam;

    /**
     * 操作状态（0正常 1异常）
     */
    @TableField("status")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;

    /**
     * 操作时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("oper_time")
    private Date operTime;

}
