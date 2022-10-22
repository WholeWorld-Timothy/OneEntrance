//package com.shardingsphere.entity;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * 发送消息内容
// */
//@Data
//public class Message implements Serializable {
//    private static final long serialVersionUID = 2921554930924807501L;
//
//    @TableId(type = IdType.ASSIGN_ID)
//    private Long msgId;
//
//    private Long contactId;
//
//    private Long userId;
//
//    private Boolean userTag;
//
//    private Date recallTime;
//
//    private String content;
//
//    private String pushMsg;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//    private Date creationTime;
//}