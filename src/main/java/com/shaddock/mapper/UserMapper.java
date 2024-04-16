package com.shaddock.mapper;

import com.shaddock.entity.User;
import com.shaddock.entity.UserToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectByLoginName(String loginName);
    Integer insertSelective(User record);

    UserToken selectByPrimaryKey(Long userId);

    User selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);
}
