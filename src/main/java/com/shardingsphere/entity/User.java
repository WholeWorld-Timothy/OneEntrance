package com.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;


@Data
public class User extends Entity<User> implements Serializable {

    private static final long serialVersionUID = -94704944729049233L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private  String name;
    private Long age;
    private  String sex;
    private  String education;

}
