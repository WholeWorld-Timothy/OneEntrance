package com.shardingsphere.algorithm;

import com.shardingsphere.DynamicDataSourceAspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @see HintManager 指定的外部路由值来筛选目标库、表
 */
public class DefaultHintShardingAlgorithm implements HintShardingAlgorithm<Long> {

    private static final Logger LOGGER = LogManager.getLogger(DefaultHintShardingAlgorithm.class);
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>();
        for (String each : availableTargetNames) {
            System.out.println("each=" + each);

            LOGGER.error("each====================="+each);

            for (Long value : shardingValue.getValues()) {
                System.out.println("value = " + value);
                LOGGER.error("value====================="+value);
                if (each.endsWith(String.valueOf(value))) {
                    result.add(each);
                }
            }
        }

        return result;
    }
}