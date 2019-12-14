package com.qt.question.service.impl;

import com.qt.question.config.QuestionConfig;
import com.qt.question.dao.mapper.JudgeTopicMapper;
import com.qt.question.global.GlobalAnswer;
import com.qt.question.global.GlobalTopic;
import com.qt.question.pojo.JudgeTopic;
import com.qt.question.service.JudgeTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "judgeTopicService")
public class JudgeTopicServiceImpl implements JudgeTopicService {

    @Autowired
    private JudgeTopicMapper judgeTopicMapper;

    @Override
    public void insertJudgeTopic(JudgeTopic judgeTopic) {
        judgeTopicMapper.insertJudgeTopic(judgeTopic);
    }

    @Override
    public void deleteJudgeTopic(Integer topicId) {
        judgeTopicMapper.deleteJudgeTopic(topicId);
    }

    @Override
    public JudgeTopic getJudgeTopic(Integer topicId) {
        JudgeTopic judgeTopic =  GlobalTopic.getJudgeTopic(topicId);
        if(judgeTopic == null){
            judgeTopic = judgeTopicMapper.getJudgeTopic(topicId);
        }
        return judgeTopic;
    }

    @Override
    public void updateJudgeTopic(JudgeTopic topic) {
        judgeTopicMapper.updateJudgeTopic(topic);
    }

    @Override
    public String getJudgeTopicAnswer(Integer topicId) {
        return judgeTopicMapper.getJudgeTopicAnswer(topicId);
    }

    @Override
    public Map<Integer, String> getAllJudgeTopicAnswer() {
        return judgeTopicMapper.getAllJudgeTopicAnswer();
    }

    @Override
    public int getJudgeTopicCount() {
        return judgeTopicMapper.getJudgeTopicsCount();
    }

    @Override
    public int getJudgeScore(String[] judge_answers, int[] judgeTopicNumbers) {
        if (judge_answers == null || judgeTopicNumbers == null) {
            throw new RuntimeException("参数非法");
        }
        if (judge_answers.length != judgeTopicNumbers.length) {
            throw new RuntimeException("数组长度不一致");
        }
        int judgeScore = 0;
        for (int i = 0; i < judgeTopicNumbers.length; i++) {
            //全局变量获取题目
            String judgeTopicAnswer = GlobalAnswer.getJudgeAnswer(judgeTopicNumbers[i]);
            if (judgeTopicAnswer != null && !"".equals(judgeTopicAnswer)) {
                if (judgeTopicAnswer.equals(judge_answers[i])) {
                    judgeScore += QuestionConfig.JUDGE_SCORE;
                }
            }
        }
        return judgeScore;
    }

    @Override
    public List<JudgeTopic> getAllJudgeTopics() {
        return judgeTopicMapper.getAllJudgeTopics();
    }
}
