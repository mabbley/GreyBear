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
 * 登陆日志
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_login_log")
public class LoginLogs extends MyBatisEntity<Long> {

    /**
     * 用户账号
     */
    @TableField("login_user")
    private String loginUser;

    /**
     * 登录状态 0成功 1失败
     */
    @TableField("status")
    private Integer status;

    /**
     * 登录IP地址
     */
    @TableField("ip_addr")
    private String ipaddr;

    /**
     * 浏览器类型
     */
    @TableField("browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField("os")
    private String os;

    /**
     * 提示消息
     */
    @TableField("msg")
    private String msg;

    /**
     * 访问时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("login_time")
    private Date loginTime;
}
