package com.shaddock.mapper;

import com.shaddock.api.app.vo.UserSocialInformationCountVo;
import com.shaddock.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    User selectByLoginName(String loginName);
    Integer insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    User selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);

    UserSocialInformationCountVo getUserSocialInformationCount(Long userId);
}
