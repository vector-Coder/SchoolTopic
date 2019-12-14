package com.qt.question.dao.mapper;

import com.qt.question.pojo.JudgeTopic;
import com.qt.question.pojo.MultipartTopic;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 *
 */
public interface MultipartTopicMapper {

    /**
     * 插入一个题目信息
     *
     * @param multipartTopic 多选题对象
     */
    void insertMultipartTopic(MultipartTopic multipartTopic);

    /**
     * 更新多选题题目信息
     *
     * @param multipartTopic 多选题对象
     */
    void updateMultipartTopic(MultipartTopic multipartTopic);

    /**
     * 获得一个题目的信息
     *
     * @param topicId 题目id
     * @return MultipartTopic
     */
    MultipartTopic getMultipartTopic(Integer topicId);

    /**
     * 删除一个多选题目
     *
     * @param topicId 多选题对象
     */
    void deleteMultipartTopic(Integer topicId);

    /**
     * 获得一个题目的答案
     *
     * @param topicId 题目id
     * @return String
     */
    String getMultipartTopicAnswer(Integer topicId);

    /**
     * 获得全部题目的答案信息
     *
     * @return Map<Integer, String>
     */
    @MapKey("topicId")
    Map<Integer, String> getAllMultipartTopicAnswer();


    /**
     * 获得多选题的数量
     * @return int
     */
    int getMultipartTopicCount();

    /**
     * 获得全部的多选题信息
     * @return Map<Integer, MultipartTopic>
     */
    @MapKey(value = "topicId")
    List<MultipartTopic>  getAllMultipartTopics();
}
