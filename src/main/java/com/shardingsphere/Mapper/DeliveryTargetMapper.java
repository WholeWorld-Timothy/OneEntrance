package com.shardingsphere.Mapper;

import com.shardingsphere.DataSourceType;
import com.shardingsphere.TargetDataSource;
import com.shardingsphere.entity.User;

public interface DeliveryTargetMapper {
    @TargetDataSource(value = DataSourceType.DATASOURCE_1)
    User getUserById();
}
