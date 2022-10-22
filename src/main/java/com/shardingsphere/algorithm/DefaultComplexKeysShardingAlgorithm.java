package com.shardingsphere.algorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

public class DefaultComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {

        // 多片键的精确值场景
        shardingValue.getColumnNameAndShardingValuesMap();

        // 多片键的范围值场景
        shardingValue.getColumnNameAndRangeValuesMap();

        // 根据以上两种情况来筛选数据节点： 数据源、物理表...

        return availableTargetNames;
    }
}