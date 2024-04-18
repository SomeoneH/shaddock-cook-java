package com.shaddock.common;

public enum ServiceResultEnum {
    ERROR("error"),
    SUCCESS("success"),
    LOGIN_NAME_IS_NOT_PHONE("请输入正确的手机号！"),
    TWO_PASSWORD_IS_NOT_MATCH("两次密码输入不一致"),
    SAME_LOGIN_NAME_EXIST("用户名已存在！"),
    DB_ERROR("数据库错误！"),
    LOGIN_ERROR("登录失败！"),
    NOT_LOGIN_ERROR("未登录"),
    TOKEN_EXPIRE_ERROR("无效认证！请重新登录！"),
    USER_NULL_ERROR("无效用户！请重新登录！");

    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
