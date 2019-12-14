package com.qt.question.web.interceptor;

import com.qt.question.config.QuestionConfig;
import com.qt.question.config.TokenConfig;
import com.qt.question.util.token.TokenUtil;
import com.qt.question.web.controller.TopicController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author vector
 * @since 2019/10/10
 * <p>
 * <p>
 * 默认拦截器
 */
public class DefaultInterceptor implements HandlerInterceptor {

    //日志打印工具
    private final static Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if (token == null) {
            logger.info("token不存在,请求拒绝");
            return false;
        }
        String studentId = tokenUtil.getParamByToken(token, TokenConfig.LOGIN_ID);
        if (tokenUtil.isValid(token)) {
            String dateStr = tokenUtil.getParamByToken(token, TokenConfig.LOGIN_TIME);
            Date loginTime = new Date(Long.parseLong(dateStr));
            //登录时间  + 限制的时间 小于 当前时间  就是超时答题 拒绝
            if (loginTime.getTime() + QuestionConfig.TEST_TIME > new Date().getTime()) {
                return true;
            }
            //请求转发
            logger.info(studentId + "发起请求,token答题超时,请求refuse");
            request.getRequestDispatcher("/Topic/refuse").forward(request, response);
            return false;
        } else {
            logger.info(studentId + "发起请求，token失效,请求reject");
            request.getRequestDispatcher("/Topic/reject").forward(request, response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
