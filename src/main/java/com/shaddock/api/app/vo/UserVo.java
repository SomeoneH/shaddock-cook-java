package com.shaddock.api.app.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private Integer followersCount;//粉丝数
    private Integer subscribeCount;//关注数
    private Integer momentCount;//动态数
}
