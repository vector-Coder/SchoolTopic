package com.qt.question.service;

import com.qt.question.exception.BaseException;
import com.qt.question.pojo.JudgeTopic;

import java.util.List;
import java.util.Map;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 *
 */
public interface JudgeTopicService {

    /**
     * 插入一个判断题题目
     *
     * @param judgeTopic 判断题
     */
    void insertJudgeTopic(JudgeTopic judgeTopic);

    /**
     * 删除一个判断题
     *
     * @param topicId 判断题id
     */
    void deleteJudgeTopic(Integer topicId);

    /**
     * 获得一幕信息
     *
     * @param topicId 题目id
     * @return JudgeTopic
     */
    JudgeTopic getJudgeTopic(Integer topicId);

    /**
     * 更新题目的信息
     *
     * @param topic 题目对象
     */
    void updateJudgeTopic(JudgeTopic topic);


    /**
     * 获得一个题目的答案
     *
     * @param topicId 题目id
     * @return String
     */
    String getJudgeTopicAnswer(Integer topicId);


    /**
     * 获得全部判断题题目的答案
     *
     * @return Map<Integer, String>
     */
    Map<Integer, String> getAllJudgeTopicAnswer();

    /**
     * 获得题目的数量
     * @return int
     */
    int getJudgeTopicCount();

    /**
     * 计算判断题的分数
     * @param judgeAnswers 判断题答案
     * @param judgeTopicNumbers 判断题题号
     * @return int
     */
    int getJudgeScore(String[] judgeAnswers, int[] judgeTopicNumbers) throws BaseException;

    /**
     * 获得全部判定题
     * @return Map<Integer, JudgeTopic>
     */
    List<JudgeTopic> getAllJudgeTopics();
}
