package com.qt.question.service.impl;

import com.qt.question.config.QuestionConfig;
import com.qt.question.dao.mapper.RadioTopicMapper;
import com.qt.question.global.GlobalAnswer;
import com.qt.question.global.GlobalTopic;
import com.qt.question.pojo.RadioTopic;
import com.qt.question.service.RadioTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(value = "radioTopicService")
public class RadioTopicServiceImpl implements RadioTopicService {

    //日志打印工具
    private final static Logger logger = LoggerFactory.getLogger(RadioTopicServiceImpl.class);

    @Autowired
    private RadioTopicMapper radioTopicMapper;

    @Override
    public void insertRadioTopic(RadioTopic radioTopic) {
        radioTopicMapper.insertRadioTopic(radioTopic);
    }

    @Override
    public void updateRadioTopic(RadioTopic radioTopic) {
        radioTopicMapper.updateRadioTopic(radioTopic);
    }

    @Override
    public RadioTopic getRadioTopic(Integer topicId) {
        RadioTopic radioTopic = GlobalTopic.getRadioTopic(topicId);
        if (radioTopic == null) {
            radioTopic = radioTopicMapper.getRadioTopic(topicId);
        }
        logger.info("获得题目：" + radioTopic);
        return radioTopic;
    }

    @Override
    public void deleteRadioTopic(Integer topicId) {
        radioTopicMapper.deleteRadioTopic(topicId);
        logger.info("删除单选题目： " + topicId);
    }

    @Override
    public String getRadioTopicAnswer(Integer topicId) {
        String answer = radioTopicMapper.getRadioTopicAnswer(topicId);
        logger.info("获取单选答案： " + answer);
        return answer;
    }

    @Override
    public Map<Integer, String> getAllRadioTopicAnswer() {
        logger.info("获取全部单选题目");
        return radioTopicMapper.getAllRadioTopicAnswer();
    }

    @Override
    public int getRadioCount() {
        logger.info("获取全部单选题目的数量");
        return radioTopicMapper.getRadioTopicCount();
    }

    @Override
    public int getRadioScore(String[] radio_Answers, int[] radioTopicNumbers) {
        if (radio_Answers == null || radioTopicNumbers == null) {
            logger.info("参数非法");
            throw new RuntimeException("参数非法");
        }
        if (radio_Answers.length != radioTopicNumbers.length) {
            logger.info("数组长度不一致");
            throw new RuntimeException("数组长度不一致");
        }
        int radioScore = 0;
        for (int i = 0; i < radioTopicNumbers.length; i++) {
            String radioAnswer = GlobalAnswer.getRadioAnswer(radioTopicNumbers[i]);
            if (radioAnswer != null) {
                if (radioAnswer.equals(radio_Answers[i])) {
                    radioScore += QuestionConfig.RADIO_SCORE;
                }
            }
        }
        return radioScore;
    }

    @Override
    public List<RadioTopic> getAllRadioTopics() {
        return radioTopicMapper.getAllRadioTopics();
    }
}
