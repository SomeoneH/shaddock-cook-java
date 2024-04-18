package com.shaddock.config.handler;

import com.shaddock.common.Constants;
import com.shaddock.common.ServiceResultEnum;
import com.shaddock.common.ShaddockException;
import com.shaddock.config.annotation.TokenToUser;
import com.shaddock.entity.User;
import com.shaddock.entity.UserToken;
import com.shaddock.mapper.UserMapper;
import com.shaddock.mapper.UserTokenMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.IOException;

@Component
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    public TokenToUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
            User user = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {
                UserToken userToken = userTokenMapper.selectByToken(token);
                if (userToken == null || userToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    ShaddockException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                user = userMapper.selectByPrimaryKey(userToken.getUserId());
                if (user == null) {
                    ShaddockException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                return user;
            } else {
                ShaddockException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}
