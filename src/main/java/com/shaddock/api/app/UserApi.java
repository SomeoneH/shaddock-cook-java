package com.shaddock.api.app;

import com.shaddock.api.app.param.UserLoginParam;
import com.shaddock.api.app.param.UserRegisterParam;
import com.shaddock.common.Constants;
import com.shaddock.common.ServiceResultEnum;
import com.shaddock.service.UserService;
import com.shaddock.util.NumberUtil;
import com.shaddock.util.Result;
import com.shaddock.util.ResultGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserRegisterParam userRegisterParam) {
        if (!NumberUtil.isPhone(userRegisterParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        } else if (!userRegisterParam.getPassword().equals(userRegisterParam.getConfirmPassword())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.TWO_PASSWORD_IS_NOT_MATCH.getResult());
        }
        String registerResult = userService.register(userRegisterParam.getLoginName(), userRegisterParam.getPassword());

        //注册成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //注册失败
        return ResultGenerator.genFailResult(registerResult);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid UserLoginParam userLoginParam) {
        if (!NumberUtil.isPhone(userLoginParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String loginResult = userService.login(userLoginParam.getLoginName(), userLoginParam.getPassword());

        //登录成功
        if (StringUtils.hasText(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }
}
