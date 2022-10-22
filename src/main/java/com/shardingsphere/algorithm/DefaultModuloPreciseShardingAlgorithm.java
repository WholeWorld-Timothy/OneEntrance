package com.shardingsphere.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class DefaultModuloPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String modulo = String.valueOf(shardingValue.getValue() % availableTargetNames.size());

        for (String targetName : availableTargetNames) {
            if (targetName.endsWith(modulo)) return targetName;
        }

        throw new UnsupportedOperationException();
    }
}