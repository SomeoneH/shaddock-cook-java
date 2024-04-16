package com.shaddock.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long userId; //主键ID
    private String loginName; //用户名
    private String password; //密码
    private String nickName; //昵称
    private String introduceSign; //个性签名
    private String userPic; //用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
