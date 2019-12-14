package com.qt.question.global;

import com.qt.question.pojo.JudgeTopic;
import com.qt.question.pojo.MultipartTopic;
import com.qt.question.pojo.RadioTopic;

import java.util.HashMap;
import java.util.Map;

public class GlobalTopic {

    //判断题容器
    private static Map<Integer, JudgeTopic> judgeTopicMap;

    //单选题容器
    private static Map<Integer, RadioTopic> radioTopicMap;

    //多选题容器
    private static Map<Integer, MultipartTopic> multipartTopicMap;

    /**
     * 初始化多选题容器
     *
     * @param judgeTopicMap 多选题容器
     */
    public static void initJudgeTopicMap(Map<Integer, JudgeTopic> judgeTopicMap) {
        System.out.println("判断题题目预加载完成：" + judgeTopicMap.size());
        GlobalTopic.judgeTopicMap = judgeTopicMap;
    }

    /**
     * 初始化单选题容器
     *
     * @param radioTopicMap 单选题容器
     */
    public static void initRadioTopicMap(Map<Integer, RadioTopic> radioTopicMap) {
        System.out.println("单选题题目预加载完成：" + radioTopicMap.size());
        GlobalTopic.radioTopicMap = radioTopicMap;
    }

    /**
     * 初始化多选题容器
     *
     * @param multipartTopicMap 多选题容器
     */
    public static void initMultipartTopicMap(Map<Integer, MultipartTopic> multipartTopicMap) {
        System.out.println("多选题题目预加载完成：" + multipartTopicMap.size());
        GlobalTopic.multipartTopicMap = multipartTopicMap;
    }

    /**
     * 多的判断题
     *
     * @param key 题号
     * @return JudgeTopic
     */
    public static JudgeTopic getJudgeTopic(Integer key) {
        if (judgeTopicMap == null) throw new RuntimeException("未初始化");
        return judgeTopicMap.getOrDefault(key, null);
    }

    /**
     * 获得单选题
     *
     * @param key 单选题号
     * @return RadioTopic
     */
    public static RadioTopic getRadioTopic(Integer key) {
        if (radioTopicMap == null) throw new RuntimeException("未初始化");
        return radioTopicMap.getOrDefault(key, null);
    }

    /**
     * 获得多选题容器
     *
     * @param key 多选题号
     * @return MultipartTopic
     */
    public static MultipartTopic getMultipartTopic(Integer key) {
        if (multipartTopicMap == null) throw new RuntimeException("未初始化");
        return multipartTopicMap.getOrDefault(key, null);
    }
}
