package com.qt.question.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vector
 * @since 2019/10/10
 * <p>
 * <p>
 * 全局的答案信息类
 */
@Component
public class GlobalAnswer {
    private static Map<Integer, String> radioMap;

    private static Map<Integer, String> judgeMap;

    private static Map<Integer, String> multipartMap;

    /**
     * 设置单选答案
     *
     * @param radioMapTemp 单选集合
     */
    public static void initRadioMap(Map<Integer, String> radioMapTemp) {
        radioMap = radioMapTemp;
    }

    /**
     * 设置多选答案
     *
     * @param multipartMapTemp 多选集合
     */
    public static void initMultipartMap(Map<Integer, String> multipartMapTemp) {
        multipartMap = multipartMapTemp;
    }

    /**
     * 设置按断答案
     *
     * @param judgeMapTemp 判断集合
     */
    public static void initJudgeMap(Map<Integer, String> judgeMapTemp) {
        judgeMap = judgeMapTemp;
    }


    /**
     * 获得单选的答案
     *
     * @param key 题目的键
     * @return String
     */
    public static String getRadioAnswer(int key) {
        if (radioMap == null) throw new RuntimeException("未初始化");
        return radioMap.getOrDefault(key, null);
    }

    /**
     * 获得判断的答案
     *
     * @param key 题目的键
     * @return String
     */
    public static String getJudgeAnswer(int key) {
        if (judgeMap == null) throw new RuntimeException("未初始化");
        return judgeMap.getOrDefault(key, null);
    }

    /**
     * 获得多选的答案
     *
     * @param key 题目的键
     * @return String
     */
    public static String getMultipartAnswer(int key) {
        if (multipartMap == null) throw new RuntimeException("未初始化");
        return multipartMap.getOrDefault(key, null);
    }
}
