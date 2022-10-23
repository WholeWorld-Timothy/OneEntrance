package com.shardingsphere.controller;



import com.shardingsphere.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;


@RestController
@RequestMapping("/message")
public class MessageController {
    private static final Logger LOGGER = LogManager.getLogger(MessageController.class);

    @PostMapping("/add")
    public int add(){
        User user = new User();
        user.setName("jiangkun11");
        int randomNum = (int) (Math.random() * 9000 + 1000);
        user.setAge(new Long(randomNum));
        user.setSex("1");
        user.setEducation("1");
        user.setCreationTime(new Date());
        user.setUpdateTime(new Date());
        //user.setId(1549220056852897794L);

        HintManager.getInstance().addDatabaseShardingValue("ds-1",1);

        LOGGER.error(HintManager.getDatabaseShardingValues());
        //userMapper.insert(user);
        return 1;
    }

}
