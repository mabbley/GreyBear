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
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public void insertFill(MetaObject metaObject) {
        Object id = getFieldValByName("id", metaObject);
        if(id==null){
            setFieldValByName("id",snowflakeIdWorker.nextId(),metaObject);
        }

        Object del = getFieldValByName("del", metaObject);
        if(del==null){
            setFieldValByName("del",0,metaObject);
        }

        Object createDate = getFieldValByName("createDate", metaObject);
        if(createDate==null){
            setFieldValByName("createDate",new Date(),metaObject);
        }

        Object updateDate = getFieldValByName("updateDate", metaObject);
        if(updateDate==null){
            setFieldValByName("updateDate",new Date(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateDate = getFieldValByName("updateDate", metaObject);
        if(updateDate==null){
            setFieldValByName("updateDate",new Date(),metaObject);
        }
    }
}
