package com.qt.question.init;


import com.qt.question.config.QuestionConfig;
import com.qt.question.global.GlobalAnswer;
import com.qt.question.global.GlobalTopic;
import com.qt.question.pojo.JudgeTopic;
import com.qt.question.pojo.MultipartTopic;
import com.qt.question.pojo.RadioTopic;
import com.qt.question.service.JudgeTopicService;
import com.qt.question.service.MultipartTopicService;
import com.qt.question.service.RadioTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SystemInit implements InitializingBean {

    //日志打印工具
    private final static Logger logger = LoggerFactory.getLogger(SystemInit.class);

    @Autowired
    private JudgeTopicService judgeTopicService;

    @Autowired
    private RadioTopicService radioTopicService;

    @Autowired
    private MultipartTopicService multipartTopicService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("加载配置信息");
        System.err.println("加载配置信息");
        //初始化系统配置  题目s数量等等信息
        QuestionConfig.JUDGE_TOPIC_TOTAL_NUM = judgeTopicService.getJudgeTopicCount();
        QuestionConfig.MULTIPART_TOPIC_TOTAL_NUM = multipartTopicService.getMultipartCount();
        QuestionConfig.RADIO_TOPIC_TOTAL_NUM = radioTopicService.getRadioCount();
        logger.info("加载全部题目信息");
        System.err.println("加载全部题目信息");
        //初始化判断题的信息
        List<JudgeTopic> judgeTopicList = judgeTopicService.getAllJudgeTopics();
        Map<Integer, JudgeTopic> judgeTopicMap = new HashMap<>(judgeTopicList.size());
        Map<Integer, String> judeAnswer = new HashMap<>(judgeTopicList.size());
        for (JudgeTopic judgeTopic : judgeTopicList) {
            judgeTopicMap.put(judgeTopic.getTopicId(), judgeTopic);
            judeAnswer.put(judgeTopic.getTopicId(), judgeTopic.getTopicResult());
        }
        GlobalTopic.initJudgeTopicMap(judgeTopicMap);
        GlobalAnswer.initJudgeMap(judeAnswer);
        //初始化多选题等信息
        List<MultipartTopic> multipartTopicList = multipartTopicService.getAllMultipartTopics();
        Map<Integer, MultipartTopic> multipartTopicHashMap = new HashMap<>(multipartTopicList.size());
        Map<Integer, String> multipartAnswer = new HashMap<>(multipartTopicList.size());
        for (MultipartTopic multipartTopic : multipartTopicList) {
            multipartTopicHashMap.put(multipartTopic.getTopicId(), multipartTopic);
            multipartAnswer.put(multipartTopic.getTopicId(), multipartTopic.getTopicResult());
        }
        GlobalTopic.initMultipartTopicMap(multipartTopicHashMap);
        GlobalAnswer.initMultipartMap(multipartAnswer);
        //初始化单选题等信息
        List<RadioTopic> radioTopicList = radioTopicService.getAllRadioTopics();
        Map<Integer, RadioTopic> radioTopicHashMap = new HashMap<>(radioTopicList.size());
        Map<Integer, String> radioAnswer = new HashMap<>(radioTopicList.size());
        for (RadioTopic radioTopic : radioTopicList) {
            radioTopicHashMap.put(radioTopic.getTopicId(), radioTopic);
            radioAnswer.put(radioTopic.getTopicId(), radioTopic.getTopicResult());
        }
        GlobalTopic.initRadioTopicMap(radioTopicHashMap);
        GlobalAnswer.initRadioMap(radioAnswer);
        logger.info("配置方法执行完成");
        System.err.println("配置方法执行完成");
    }
}
