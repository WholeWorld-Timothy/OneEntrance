package com.shardingsphere;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
//import com.shardingsphere.Mapper.MessageMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingsphere.Mapper.UserMapper;
//import com.shardingsphere.entity.Message;
import com.shardingsphere.algorithm.DefaultHintShardingAlgorithm;
import com.shardingsphere.entity.User;
//import com.shardingsphere.service.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class DemoTest {

    private static final Logger LOGGER = LogManager.getLogger(DemoTest.class);
    @Autowired
    private UserMapper userMapper;


//    @Autowired
//    private MessageMapper messageMapper;
//
//
//    @Autowired
//    private MessageService messageService;

    /**
     * 测试user分库
     */
    @Test
    //@TargetDataSource(value = DataSourceType.DATASOURCE_1)
    void contextLoads() {
        User user = new User();
        user.setName("jiangkun11");
        int randomNum = (int) (Math.random() * 9000 + 1000);
        user.setAge(new Long(randomNum));
        user.setSex("1");
        user.setEducation("1");
        user.setCreationTime(new Date());
        user.setUpdateTime(new Date());
        //user.setId(1549220056852897794L);

        HintManager.getInstance().addDatabaseShardingValue("user",1);

        //如果与addDatabaseShardingValue同时打开会报错
        //HintManager.getInstance().setDatabaseShardingValue(3);

        LOGGER.error(HintManager.getDatabaseShardingValues());
        //userMapper.insert(user);

        //所查询数据在第一个库
        User user0 = userMapper.selectOne(new QueryWrapper<User>().eq("id", "1583458708281049090"));
        if(user0 != null) {
            LOGGER.error("user0 = " + user0);
        }


        //所查询数据在第二个库
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("id", "1583617906281164801"));
        if(user1 != null) {
            LOGGER.error("user1 = " + user1);
        }

//
//        //ds-0库
//        User user0 = userMapper.selectById("1583458708281049090");
//        System.err.println("ds-0库:" + JSONUtil.parseObj(user0));
//        //ds-1库
//        User user1 = userMapper.selectById("1583460007303147522");
//        System.err.println("ds-1库:" + JSONUtil.parseObj(user1));


    }


//    /**
//     * 测试Message分库
//     */
//    @Test
//    void addMessage() {
//        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
//
//
//        List<Message> messageList = new ArrayList<>();
//        for (int j = 0; j < 100; j++) {
//            Message message = new Message();
//            int randomNum = (int) (Math.random() * 9000 + 1000);
//            message.setMsgId(snowflake.nextId());
//            message.setContactId(snowflake.nextId());
//            message.setUserId(new Long(randomNum));
//            message.setUserTag(Boolean.TRUE);
//            message.setRecallTime(new Date());
//            message.setContent("测试测试");
//            message.setPushMsg("111111111111");
//            message.setCreationTime(new Date());
//            messageList.add(message);
//        }
//        System.out.println("=====================================================");
//        messageService.saveBatch(messageList);
//    }

}
