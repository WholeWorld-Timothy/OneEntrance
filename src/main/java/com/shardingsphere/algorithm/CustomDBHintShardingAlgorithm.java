package com.shardingsphere.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class CustomDBHintShardingAlgorithm implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> dataSourceNames, HintShardingValue<Long> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String tableName : dataSourceNames) {
            for (Long shardingValue : hintShardingValue.getValues()) {
                if (tableName.endsWith(String.valueOf(shardingValue % dataSourceNames.size()))) {
                    result.add(tableName);
                }
            }
        }
        System.out.println("result = " + result);
        return result;
    }
}

