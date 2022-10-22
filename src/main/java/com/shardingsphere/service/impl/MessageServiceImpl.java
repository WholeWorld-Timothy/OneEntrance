//package com.shardingsphere.service.impl;
//
//import cn.hutool.core.lang.Snowflake;
//import cn.hutool.core.util.IdUtil;
//import com.baomidou.mybatisplus.core.mapper.Mapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.shardingsphere.Mapper.MessageMapper;
//import com.shardingsphere.Mapper.UserMapper;
//import com.shardingsphere.entity.Message;
//import com.shardingsphere.entity.User;
//import com.shardingsphere.service.MessageService;
//import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
//import org.apache.shardingsphere.transaction.core.TransactionType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//
//
//@Service
//public class MessageServiceImpl  extends ServiceImpl<MessageMapper, Message>  implements MessageService {
//
//
//    @Autowired
//    private UserMapper userMapper;
//
//
//    @Autowired
//    private MessageMapper messageMapper;
//
//
//
//    @Transactional(rollbackFor = Exception.class)
//    @ShardingTransactionType(TransactionType.XA)
//    @Override
//    public int add() {
//        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
//        Message message = new Message();
//        int randomNum = (int) (Math.random() * 9000 + 1000);
//        message.setMsgId(snowflake.nextId());
//        message.setContactId(snowflake.nextId());
//        message.setUserId(new Long(randomNum));
//        message.setUserTag(Boolean.TRUE);
//        message.setRecallTime(new Date());
//        message.setContent("测试测试");
//        message.setPushMsg("55555555");
//        message.setCreationTime(new Date());
//        messageMapper.insert(message);
//
//        User user = new User();
//        user.setName("何志鹏555");
//        int randomNum2 = (int) (Math.random() * 9000 + 1000);
//        user.setAge(new Long(randomNum2));
//        user.setSex("2");
//        user.setEducation("1");
//        int insert = userMapper.insert(user);
//        if(insert>0){
//            //抛出异常 事务回滚
//            int a = 10 / 0;
//            return 1;
//        }
//        return 0;
//    }
//}
