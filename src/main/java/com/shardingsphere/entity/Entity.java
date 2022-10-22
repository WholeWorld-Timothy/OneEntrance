package com.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

public class Entity<T extends Entity<?>> extends Model<T> {

    public final static String META_CREATED = "creationTime";
    public final static String META_UPDATE = "updateTime";

    @TableField(fill = FieldFill.INSERT)
    private Date creationTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}