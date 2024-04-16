package com.shaddock.service;

public interface UserService {
    // 用户注册
    String register(String loginName, String password);

    String login(String loginName, String password);
}
