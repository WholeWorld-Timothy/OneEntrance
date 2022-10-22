package com.shardingsphere;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
//import com.shardingsphere.Mapper.MessageMapper;
import com.shardingsphere.Mapper.UserMapper;
//import com.shardingsphere.entity.Message;
import com.shardingsphere.entity.User;
//import com.shardingsphere.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 何志鹏
 * @Date 2022/7/15 15:15
 * @Version 1.0
 */
@SpringBootTest
public class DemoTest {


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
    void contextLoads() {
        User user = new User();
        user.setName("jiangkun");
        int randomNum = (int) (Math.random() * 9000 + 1000);
        user.setAge(new Long(randomNum));
        user.setSex("1");
        user.setEducation("1");
        user.setCreationTime(new Date());
        user.setUpdateTime(new Date());
        //user.setId(1549220056852897794L);
        userMapper.insert(user);

        //ds-0库
        User user0 = userMapper.selectById("1583458708281049090");
        System.err.println("ds-0库:" + JSONUtil.parseObj(user0));
        //ds-1库
        User user1 = userMapper.selectById("1583460007303147522");
        System.err.println("ds-1库:" + JSONUtil.parseObj(user1));


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
