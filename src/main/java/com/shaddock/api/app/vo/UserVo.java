package com.shaddock.api.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserVo implements Serializable {
    private Long userId; //主键ID
    private String loginName; //用户名
    private String nickName; //昵称
    private String introduceSign; //个性签名
    private String userPic; //用户头像地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;//更新时间
}
