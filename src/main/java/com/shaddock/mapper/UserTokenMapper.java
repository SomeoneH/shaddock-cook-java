package com.shaddock.mapper;

import com.shaddock.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper {

    int insertSelective(UserToken userToken);

    int updateByPrimaryKeySelective(UserToken userToken);

    UserToken selectByPrimaryKey(Long userId);

    UserToken selectByToken(String token);

    int deleteByPrimaryKey(Long userId);
}
