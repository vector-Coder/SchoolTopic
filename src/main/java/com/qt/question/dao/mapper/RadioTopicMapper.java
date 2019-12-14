package com.qt.question.dao.mapper;

import com.qt.question.pojo.RadioTopic;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 *
 */
public interface RadioTopicMapper {

    /**
     * 插入一个题目信息
     *
     * @param radioTopic 多选题对象
     */
    void insertRadioTopic(RadioTopic radioTopic);

    /**
     * 更新多选题题目信息
     *
     * @param radioTopic 多选题对象
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
     * 删除一个多选题目
     *
     * @param topicId 多选题对象
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
    @MapKey("topicId")
    Map<Integer, String> getAllRadioTopicAnswer();



    /**
     * 获得单选题的数量
     * @return int
     */
    int getRadioTopicCount();

    /**
     * 获得全部的单选题信息
     * @return Map<Integer, RadioTopic>
     */
    @MapKey(value = "topicId")
    List<RadioTopic> getAllRadioTopics();
}
