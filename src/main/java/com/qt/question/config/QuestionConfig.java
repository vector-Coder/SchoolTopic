package com.qt.question.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author vector
 * @since 2019/10/10
 * <p>
 * 系统答题的配置类
 * 答题时间
 * 分数
 * 题目数量等信息
 */

@Configuration
@PropertySource(value = "classpath:config/questionConfig.properties")
public class QuestionConfig {

    //答题的最大时间
    public static long TEST_TIME;

    //单选题数量
    public static int RADIO_TOPIC_NUM;

    //多选题目的数量
    public static int MULTIPART_TOPIC_NUM;

    //判断题目的数量
    public static int JUDGE_TOPIC_NUM;

    //单选题题目总共的数量
    public static int RADIO_TOPIC_TOTAL_NUM;

    //多选题数目的总数量
    public static int MULTIPART_TOPIC_TOTAL_NUM;

    //判断题目总数的数量
    public static int JUDGE_TOPIC_TOTAL_NUM;

    //单选分数
    public static int RADIO_SCORE;

    //多选分数
    public static int MULTIPART_SCORE;

    //判断题分数
    public static int JUDGE_SCORE;

    public static int MAX_ANSWER_COUNT;

    @Value("${question.radioScore}")
    public void setRadioScore(int radioScore) {
        RADIO_SCORE = radioScore;
    }

    @Value("${question.multipartScore}")
    public void setMultipartScore(int multipartScore) {
        MULTIPART_SCORE = multipartScore;
    }

    @Value("${question.judgeScore}")
    public void setJudgeScore(int judgeScore) {
        JUDGE_SCORE = judgeScore;
    }

    @Value("${question.radioTopicNum}")
    public void setRadioTopicNum(int radioTopicNum) {
        RADIO_TOPIC_NUM = radioTopicNum;
    }


    @Value("${question.multipartTopicNum}")
    public void setMultipartTopicNum(int multipartTopicNum) {
        MULTIPART_TOPIC_NUM = multipartTopicNum;
    }

    @Value("${question.judgeTopicNum}")
    public void setJudgeTopicNum(int judgeTopicNum) {
        JUDGE_TOPIC_NUM = judgeTopicNum;
    }

    @Value("${question.timeLimit}")
    public void setTestTime(long testTime) {
        TEST_TIME = testTime * 1000;
    }

    @Value("${question.maxAnswerCount}")
    public void setMaxAnswerCount(int max) {
        MAX_ANSWER_COUNT = max;
    }
}
