package com.qt.question.util.token.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.qt.question.config.TokenConfig;
import com.qt.question.exception.BaseException;
import com.qt.question.exception.NoSupportException;
import com.qt.question.util.token.TokenUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtilImpl implements TokenUtil {

//    第一部分我们称它为头部（header),第二部分我们称其为载荷（payload)，第三部分是签证（signature)。

    @Override
    public String getToken(Map<String, String> map) throws BaseException {
        if (map.size() > 5) {
            throw new NoSupportException("参数超出限制");
        }
        Date date = new Date(System.currentTimeMillis() + TokenConfig.TOKEN_VALID_TIME * 1000);
        Algorithm algorithm = Algorithm.HMAC256(TokenConfig.TOKEN_SALT);
        Map<String, Object> head = new HashMap<>(2);
        //输入参数
        map.put("typ", "JWT");
        map.put("alg", "HS256");
        JWTCreator.Builder build = JWT.create().withHeader(head);
        //加参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            build.withClaim(entry.getKey(), entry.getValue());
        }
        return build.withExpiresAt(date).sign(algorithm);
    }

    @Override
    public Map<String, String> parseToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, Claim> claimMap = decodedJWT.getClaims();
        Map<String, String> result = new HashMap<>(claimMap.size());
        for (Map.Entry<String, Claim> entry : claimMap.entrySet()) {
            result.put(entry.getKey(), entry.getValue().asString());
        }
        return result;
    }

    @Override
    public String getParamByToken(String token, String key) {
        Algorithm algorithm = Algorithm.HMAC256(TokenConfig.TOKEN_SALT);
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(key).asString();
    }

    @Override
    public boolean isValid(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TokenConfig.TOKEN_SALT);
        try {
            JWT.require(algorithm).build().verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
