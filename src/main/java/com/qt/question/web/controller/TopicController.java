package com.qt.question.web.controller;

import com.qt.question.config.QuestionConfig;
import com.qt.question.config.TokenConfig;
import com.qt.question.exception.BaseException;
import com.qt.question.pojo.*;
import com.qt.question.service.*;
import com.qt.question.service.impl.RadioTopicServiceImpl;
import com.qt.question.util.JsonResult;
import com.qt.question.util.Result;
import com.qt.question.util.ResultCode;
import com.qt.question.util.random.RandomUtil;
import com.qt.question.util.string.StringUtil;
import com.qt.question.util.token.TokenUtil;
import org.apache.poi.xwpf.usermodel.TOC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
//跨域请求在方法上指定请求之后才有效
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/Topic")
public class TopicController {

    //日志打印工具
    private final static Logger logger = LoggerFactory.getLogger(TopicController.class);

    //判断题业务层
    @Autowired
    private JudgeTopicService judgeTopicService;

    //单选题业务层
    @Autowired
    private RadioTopicService radioTopicService;

    //多选题业务层
    @Autowired
    private MultipartTopicService multipartTopicService;

    //token验证工具
    @Autowired
    private TokenUtil tokenUtil;

    //答案分割工具
    @Autowired
    private StringUtil stringUtil;

    //student业务层
    @Autowired
    private StudentService studentService;


    /**
     * 获得一个题目信息
     *
     * @param topicIndex 题目坐标
     * @param token      令牌
     * @return Result
     */
    @RequestMapping(value = "/getTopic", method = RequestMethod.GET)
    public JsonResult getNextTopic(Integer topicIndex, String token) {
        if (tokenUtil.isValid(token)) {
            //判断学生是否存在
            String studentId = tokenUtil.getParamByToken(token, TokenConfig.LOGIN_ID);
            try {
                Long id = Long.parseLong(studentId);
                return studentService.getTopic(id, topicIndex,token);
            } catch (Exception e) {
                return new JsonResult<>("获取失败", ResultCode.ILLEGALITY.getCode(), false);
            }
        }
        logger.info(tokenUtil.getParamByToken(token, "studentId") + " 获取题目信息,token失效");
        return new JsonResult("登录失效", ResultCode.TIME_OUT.getCode(), false);
    }


    /**
     * 计算分数
     *
     * @param radioAnswer     单选答案
     * @param multipartAnswer 多选答案
     * @param judgeAnswer     判断题答案
     * @param token           令牌
     * @return Result
     */
    @RequestMapping(value = "/calScore", method = RequestMethod.GET)
    public JsonResult calTestScore(String radioAnswer, String multipartAnswer, String judgeAnswer, String token) {
        if ("".equals(radioAnswer) && "".equals(judgeAnswer) && "".equals(multipartAnswer)) {
            logger.info("计算分数，答案为空");
            return new JsonResult("数据不合法", ResultCode.ILLEGALITY.getCode(), false);
        }

        if (tokenUtil.isValid(token)) {
            //单选答案
            String[] radio_Answers = new String[0];
            String[] multipart_Answers = new String[0];
            String[] judge_Answers = new String[0];

            int[] radioTopicNumbers = new int[0];
            int[] multipartTopicNumbers = new int[0];
            int[] judgeTopicNumbers = new int[0];
            //分割答案
            try {
                if (radioAnswer != null && !"".equals(radioAnswer.trim())) {
                    radio_Answers = stringUtil.splitAnswer(radioAnswer);
                    radioTopicNumbers = stringUtil.splitTopicNumber(radioAnswer);
                }

                if (multipartAnswer != null && !"".equals(multipartAnswer.trim())) {
                    multipartTopicNumbers = stringUtil.splitTopicNumber(multipartAnswer);
                    multipart_Answers = stringUtil.splitAnswer(multipartAnswer);
                }
                if (judgeAnswer != null && !"".equals(judgeAnswer.trim())) {
                    judgeTopicNumbers = stringUtil.splitTopicNumber(judgeAnswer);
                    judge_Answers = stringUtil.splitAnswer(judgeAnswer);
                }
            } catch (Exception e) {
                logger.info("答案分割失败：" + radioAnswer + "\n" + multipartAnswer + "\n" + judgeAnswer);
                return new JsonResult("提交失败", ResultCode.FAIL.getCode(), false);
            }
            //分离参数数据
            Map<String, String> map = tokenUtil.parseToken(token);
            String studentIdStr = map.get(TokenConfig.LOGIN_ID);
            long time;
            int studentAnswerCount;
            try {
                time = Long.parseLong(map.get(TokenConfig.LOGIN_TIME));
                studentAnswerCount = Integer.parseInt(map.get(TokenConfig.ANSWER_INDEX));
            } catch (Exception e) {
                return new JsonResult("提交失败", ResultCode.FAIL.getCode(), false);
            }
            //计算分数
            logger.info(studentIdStr + ":计算答案，单选：" + radioAnswer + "多选：" + multipartAnswer + "判断：" + judgeAnswer);
            if (studentIdStr != null) {
                long studentId = Long.parseLong(studentIdStr);
                if (studentId == 201912345678L) {
                    logger.info("测试账号登录");
                    return new JsonResult("测试账号登录", ResultCode.SUCCESS.getCode(), true);
                }
                Student student = new Student();
                student.setStudentId(studentId);
                student.setStudentAnswerCount(studentAnswerCount);
                logger.info("开始更新学生、学院、班级数据");
                JsonResult jsonResult = null;
                try {
                    jsonResult = studentService.calStudentScore(radio_Answers, radioTopicNumbers,
                            multipart_Answers, multipartTopicNumbers,
                            judge_Answers, judgeTopicNumbers,
                            student, time);
                } catch (BaseException e) {
                    logger.info("计算分数失败:" + e.toString());
                    return new JsonResult("提交失败", ResultCode.FAIL.getCode(), false);
                }
                logger.info("分数计算成功：" + jsonResult);
                return jsonResult;
            } else {
                logger.info("token参数获取异常");
                return new JsonResult("身份凭证不合法", ResultCode.ILLEGALITY.getCode(), false);
            }
        }
        logger.info("token失效");
        return new JsonResult("登录过期", ResultCode.ILLEGALITY.getCode(), false);
    }


    /**
     * 答题超时的转发地址
     *
     * @return Result
     */
    @RequestMapping(value = "/refuse")
    public Result refuse() {
        return new Result(null, "答题超时,请求失败！", false, 400);
    }

    /**
     * 登录失效的转发地址
     *
     * @return Result
     */
    @RequestMapping(value = "/reject")
    public Result reject() {
        return new Result(null, "登录失效,请重新登录！", false, 400);
    }
}
