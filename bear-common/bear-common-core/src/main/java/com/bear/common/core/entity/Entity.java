package com.bear.common.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mby on 2019/4/17.
 */
public interface Entity<ID extends Serializable> extends Serializable {

    ID getId();
    void setId(ID id);

    Integer getDel();
    void setDel(Integer del);

    Date getCreateDate();
    void setCreateDate(Date createDate);

    Date getUpdateDate();
    void setUpdateDate(Date updateDate);
}
