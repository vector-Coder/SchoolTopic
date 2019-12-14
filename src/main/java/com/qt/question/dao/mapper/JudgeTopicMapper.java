package com.qt.question.dao.mapper;

import com.qt.question.pojo.JudgeTopic;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 *
 */
public interface JudgeTopicMapper {


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
    @MapKey("topicId")
    Map<Integer, String> getAllJudgeTopicAnswer();


    /**
     * 获得判断题的数量
     * @return int
     */
    int getJudgeTopicsCount();

    /**
     * 获得全部判断题信息
     * @return  Map<Integer, JudgeTopic>
     */
    @MapKey(value = "topicId")
    List<JudgeTopic> getAllJudgeTopics();
}
