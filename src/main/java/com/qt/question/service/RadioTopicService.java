package com.qt.question.service;

import com.qt.question.exception.BaseException;
import com.qt.question.pojo.RadioTopic;

import java.util.List;
import java.util.Map;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 *
 */
public interface RadioTopicService {
    /**
     * 插入一个题目信息
     *
     * @param radioTopic 单选题对象
     */
    void insertRadioTopic(RadioTopic radioTopic);

    /**
     * 更新单选题题目信息
     *
     * @param radioTopic 单选题对象
     */
    void updateRadioTopic(RadioTopic radioTopic);

    /**
     * 获得一个题目的信息
     *
     * @param topicId 题目id
     * @return MultipartTopic
     */
    RadioTopic getRadioTopic(Integer topicId);

    /**
     * 删除一个单选题目
     *
     * @param topicId 单选题对象
     */
    void deleteRadioTopic(Integer topicId);

    /**
     * 获得一个题目的答案
     *
     * @param topicId 题目id
     * @return String
     */
    String getRadioTopicAnswer(Integer topicId);

    /**
     * 获得全部题目的答案信息
     *
     * @return Map<Integer, String>
     */
    Map<Integer, String> getAllRadioTopicAnswer();

    /**
     * 获得题目的数量
     * @return int
     */
    int getRadioCount();

    /**
     * 计算单选题的总分数
     * @return int
     */
    int getRadioScore(String[] radio_Answers, int[] radioTopicNumbers)  throws BaseException;

    /**
     * 获得全部单选题信息
     * @return  Map<Integer, RadioTopic>
     */
    List<RadioTopic> getAllRadioTopics();
}
