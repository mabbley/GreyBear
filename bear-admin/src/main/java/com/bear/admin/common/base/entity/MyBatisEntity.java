package com.bear.admin.common.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bear.common.core.entity.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyBatisEntity<ID extends Serializable> extends Model implements Entity<ID> {

    @TableId(value = "id", type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    protected ID id;

    @TableField(value = "del",fill = FieldFill.INSERT,strategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    protected Long del;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date",fill = FieldFill.INSERT,strategy = FieldStrategy.NOT_EMPTY)
    protected Date createDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE,strategy = FieldStrategy.NOT_EMPTY)
    protected Date updateDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,true);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj,true);
    }
}
