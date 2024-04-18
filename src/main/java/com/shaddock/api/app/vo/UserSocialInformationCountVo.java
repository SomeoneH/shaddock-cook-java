package com.shaddock.api.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserSocialInformationCountVo implements Serializable {
    private Integer followersCount;//粉丝数
    private Integer subscribeCount;//关注数
    private Integer momentCount;//动态数
}
