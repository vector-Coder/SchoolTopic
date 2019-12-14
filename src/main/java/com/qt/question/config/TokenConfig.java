package com.qt.question.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 * 令牌的配置类
 */
@Configuration
@PropertySource(value = "classpath:config/tokenConfig.properties")
public class TokenConfig {

    public static final String LOGIN_ID = "loginId";
    public static final String LOGIN_TIME = "loginTime";
    public static final String ANSWER_INDEX = "answerIndex";
    //token 的过期时间
    public static long TOKEN_VALID_TIME;

    //token的盐
    public static String TOKEN_SALT;

    @Value(value = "${token.tokenSalt}")
    public void setTokenSalt(String tokenSalt) {
        System.err.println("init config");
        TOKEN_SALT = tokenSalt;
    }

    @Value(value = "${token.tokenValidTime}")
    public void setTokenValidTime(int tokenValidTime) {
        TOKEN_VALID_TIME = tokenValidTime;
    }
}
