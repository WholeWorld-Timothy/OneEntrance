package com.shardingsphere.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class DefaultModuloRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        Long lower = shardingValue.getValueRange().lowerEndpoint();
        Long upper = shardingValue.getValueRange().upperEndpoint();

        Collection<String> result = new LinkedHashSet<>();
        for (Long i = lower; i <= upper; i++) {
            if (result.size() == availableTargetNames.size()) break;
            String modulo = String.valueOf(i % availableTargetNames.size());
            result.addAll(availableTargetNames.stream().filter(m -> m.endsWith(modulo)).collect(Collectors.toList()));
        }

        return result;
    }
}