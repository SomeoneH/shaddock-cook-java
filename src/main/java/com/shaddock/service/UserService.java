package com.shaddock.service;

import com.shaddock.api.app.vo.UserSocialInformationCountVo;

public interface UserService {
    // 用户注册
    String register(String loginName, String password);

    String login(String loginName, String password);

    Boolean logout(Long userId);

    UserSocialInformationCountVo getUserSocialInformationCount(Long userId);
}
