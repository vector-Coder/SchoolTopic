package com.qt.question.service.impl;

import com.qt.question.config.QuestionConfig;
import com.qt.question.dao.mapper.MultipartTopicMapper;
import com.qt.question.exception.BaseException;
import com.qt.question.exception.NoSupportException;
import com.qt.question.global.GlobalAnswer;
import com.qt.question.global.GlobalTopic;
import com.qt.question.pojo.MultipartTopic;
import com.qt.question.service.MultipartTopicService;
import com.qt.question.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "multipartService")
public class MultipartTopicServiceImpl implements MultipartTopicService {

    @Autowired
    private MultipartTopicMapper multipartTopicMapper;

    //答案分割工具
    @Autowired
    private StringUtil stringUtil;

    @Override
    public void insertMultipartTopic(MultipartTopic multipartTopic) {

    }

    @Override
    public void updateMultipartTopic(MultipartTopic multipartTopic) {

    }

    @Override
    public MultipartTopic getMultipartTopic(Integer topicId) {
        MultipartTopic multipartTopic = GlobalTopic.getMultipartTopic(topicId);
        if(multipartTopic == null){
            multipartTopic = multipartTopicMapper.getMultipartTopic(topicId);
        }
        return multipartTopic;
    }

    @Override
    public void deleteMultipartTopic(Integer topicId) {
        multipartTopicMapper.deleteMultipartTopic(topicId);
    }

    @Override
    public String getMultipartTopicAnswer(Integer topicId) {
        return multipartTopicMapper.getMultipartTopicAnswer(topicId);
    }

    @Override
    public Map<Integer, String> getAllMultipartTopicAnswer() {
        return multipartTopicMapper.getAllMultipartTopicAnswer();
    }

    @Override
    public int getMultipartCount() {
        return multipartTopicMapper.getMultipartTopicCount();
    }

    @Override
    public  int getMultipartScore(String[] multipart_answers, int[] multipartTopicNumbers) throws BaseException {
        if(multipart_answers == null || multipartTopicNumbers== null){
            throw new NoSupportException("参数非法");
        }
        if(multipart_answers.length != multipartTopicNumbers.length){
            throw new NoSupportException("数组长度不一致");
        }
        int multipartScore = 0;
        for (int i=0; i<multipartTopicNumbers.length; i++) {
            String multiTopicAnswer = GlobalAnswer.getMultipartAnswer(multipartTopicNumbers[i]);
            if(multiTopicAnswer != null && !"".equals(multiTopicAnswer)){
                //将答案排序
                String sortAnswer = stringUtil.sortMultipartAnswer(multipart_answers[i]);
                if (multiTopicAnswer.equals(sortAnswer)) {
                    multipartScore += QuestionConfig.MULTIPART_SCORE;
                }
            }
        }
        return multipartScore;
    }

    @Override
    public List<MultipartTopic> getAllMultipartTopics() {
        return multipartTopicMapper.getAllMultipartTopics();
    }
}
