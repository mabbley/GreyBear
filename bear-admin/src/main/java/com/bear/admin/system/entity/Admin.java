package com.bear.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bear.admin.common.base.entity.MyBatisEntity;
import com.bear.admin.common.enums.AdminStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 管理员
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_admin")
public class Admin extends MyBatisEntity<Long>  {

    /**
     *  机构
     */
    @TableField("org_id")
    private Long orgId;

    /**
     *  账号
     */
    @TableField("login_user")
    private String loginUser;

    /**
     *  密码
     */
    @TableField("login_pwd")
    private String loginPwd;

    /**
     *  姓名
     */
    @TableField("full_name")
    private String fullName;

    /**
     *  邮件地址
     */
    @TableField("email")
    private String email;

    /**
     *  电话号码
     */
    @TableField("phone")
    private String phone;

    /**
     *  末次登陆IP
     */
    @TableField("last_login_ip")
    private String lastLoginIp;

    /**
     *  末次登陆时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     *  状态{NORMAL("正常"), LOCKING("锁定"), DISABLE("禁用")}
     */
    @TableField("status")
    private AdminStatus status;
}
