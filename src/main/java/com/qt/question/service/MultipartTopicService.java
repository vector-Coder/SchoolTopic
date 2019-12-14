package com.qt.question.service;

import com.qt.question.exception.BaseException;
import com.qt.question.exception.NoSupportException;
import com.qt.question.pojo.MultipartTopic;

import java.util.List;
import java.util.Map;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 */
public interface MultipartTopicService {

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
    Map<Integer, String> getAllMultipartTopicAnswer();


    /**
     * 获得题目的数量
     *
     * @return int
     */
    int getMultipartCount();

    /**
     * 获得多选题的分数
     *
     * @param multipart_answers
     * @param multipartTopicNumbers
     * @return
     */
    int getMultipartScore(String[] multipart_answers, int[] multipartTopicNumbers) throws BaseException;

    /**
     * 获得全部多选题
     *
     * @return Map<Integer, MultipartTopic>
     */
    List<MultipartTopic> getAllMultipartTopics();
}
