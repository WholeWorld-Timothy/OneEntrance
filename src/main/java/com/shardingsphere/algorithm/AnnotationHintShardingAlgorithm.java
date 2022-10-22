//package com.shardingsphere.algorithm;
//
//import org.apache.shardingsphere.api.sharding.ShardingValue;
//import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//
//public class AnnotationHintShardingAlgorithm implements HintShardingAlgorithm {
//
//
//    private final static Logger logger = LoggerFactory.getLogger(AnnotationHintShardingAlgorithm.class);
//
//    @Override
//    public Collection<String> doSharding(Collection<String> availableTargetNames, ShardingValue shardingValue) {
//        if (shardingValue instanceof ListShardingValue && !CollectionUtils.isEmpty(((ListShardingValue) shardingValue).getValues())) {
//            logger.info("---------------------"+((ListShardingValue) shardingValue).getValues());
//            return availableTargetNames.stream().filter(availableTargetName ->
//                    ((ListShardingValue) shardingValue).getValues().contains(availableTargetName)).collect(Collectors.toList());
//        }
//        return availableTargetNames;
//    }
//}