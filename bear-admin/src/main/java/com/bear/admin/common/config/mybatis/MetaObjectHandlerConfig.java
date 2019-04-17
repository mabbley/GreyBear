package com.bear.admin.common.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.bear.common.core.snowflake.SnowflakeIdWorker;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mby on 2019/4/17.
 */
@Component
public class MetaObjectHandlerConfig extends MetaObjectHandler {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("id",snowflakeIdWorker.nextId(),metaObject);
        setFieldValByName("del",0L,metaObject);
        setFieldValByName("createDate",new Date(),metaObject);
        setFieldValByName("updateDate",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateDate",new Date(),metaObject);
    }
}
