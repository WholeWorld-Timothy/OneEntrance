package com.shardingsphere.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shardingsphere.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper  extends BaseMapper<User> {

}
