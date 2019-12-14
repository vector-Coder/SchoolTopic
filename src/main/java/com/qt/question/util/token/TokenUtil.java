package com.qt.question.util.token;

import com.qt.question.exception.BaseException;

import java.util.Map;

/**
 * token接口
 */
public interface TokenUtil {

    /**
     * 传入参数 生成一个token
     *
     * @param map 参数map
     * @return String
     */
    String getToken(Map<String, String> map) throws  BaseException;

    /**
     * 从token中提取信息
     *
     * @param token Token
     * @return Map
     */
    Map<String, String> parseToken(String token);

    /**
     * 从token中获取指定的参数信息
     *
     * @param token Token
     * @param key   参数的值
     * @return String
     */
    String getParamByToken(String token, String key);


    /**
     * 校验token的有效性
     *
     * @param token token
     * @return  Boolean
     */
    boolean isValid(String token);
}
