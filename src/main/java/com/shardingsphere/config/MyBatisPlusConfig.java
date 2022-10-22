package com.shardingsphere.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.shardingsphere.entity.Entity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MyBatisPlusConfig implements MetaObjectHandler {


    /**
     * 创建时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            if (entity.getCreationTime() == null) {
                this.setFieldValByName(Entity.META_CREATED, Calendar.getInstance().getTime(), metaObject);
            }
        }
    }

    /**
     * 更新时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            if (entity.getUpdateTime() == null) {
                this.setFieldValByName(Entity.META_UPDATE, Calendar.getInstance().getTime(), metaObject);
            }
        }
    }

    /**
     * 分页
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}