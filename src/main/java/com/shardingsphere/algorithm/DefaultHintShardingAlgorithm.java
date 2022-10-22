package com.shardingsphere.algorithm;

import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @see HintManager 指定的外部路由值来筛选目标库、表
 */
public class DefaultHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>();
        for (String each : availableTargetNames) {
            for (Long value : shardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value))) {
                    result.add(each);
                }
            }
        }

        return result;
    }
}