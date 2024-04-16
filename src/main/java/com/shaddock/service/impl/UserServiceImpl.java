package com.shaddock.service.impl;

import com.shaddock.common.Constants;
import com.shaddock.common.ServiceResultEnum;
import com.shaddock.entity.User;
import com.shaddock.entity.UserToken;
import com.shaddock.mapper.UserMapper;
import com.shaddock.mapper.UserTokenMapper;
import com.shaddock.service.UserService;
import com.shaddock.util.MD5Util;
import com.shaddock.util.NumberUtil;
import com.shaddock.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Override
    public String register(String loginName, String password) {
        // 查询用户是否存在
        System.out.println(userMapper.selectByLoginName(loginName));
        if (userMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        User addUser = new User();
        addUser.setLoginName(loginName);
        addUser.setNickName(loginName);
        addUser.setIntroduceSign(Constants.USER_Default_INTRO);
        //String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        //addUser.setPassword(passwordMD5);
        // 前端传过来的参数已经做了md5加密
        addUser.setPassword(password);
        addUser.setCreateTime(LocalDateTime.now());
        addUser.setUpdateTime(LocalDateTime.now());
        if (userMapper.insertSelective(addUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String password) {
        User user = userMapper.selectByLoginNameAndPassword(loginName, password);
        if (user != null) {
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", user.getUserId());
            UserToken userToken = userTokenMapper.selectByPrimaryKey(user.getUserId());
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时
            if (userToken == null) {
                userToken = new UserToken();
                userToken.setUserId(user.getUserId());
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                //新增一条token数据
                if (userTokenMapper.insertSelective(userToken) > 0) {
                    //新增成功后返回
                    return token;
                }
            } else {
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                //更新
                if (userTokenMapper.updateByPrimaryKeySelective(userToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }
}
