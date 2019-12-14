package com.qt.question.service.impl;

import com.qt.question.config.QuestionConfig;
import com.qt.question.config.TokenConfig;
import com.qt.question.dao.mapper.StudentMapper;
import com.qt.question.exception.BaseException;
import com.qt.question.exception.NoSupportException;
import com.qt.question.pojo.*;
import com.qt.question.service.*;
import com.qt.question.util.JsonResult;
import com.qt.question.util.Result;
import com.qt.question.util.ResultCode;
import com.qt.question.util.random.RandomUtil;
import com.qt.question.util.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(value = "/studentService")
public class StudentServiceImpl implements StudentService {

    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private JudgeTopicService judgeTopicService;

    @Autowired
    private RadioTopicService radioTopicService;

    @Autowired
    private MultipartTopicService multipartTopicService;


    @Autowired
    private GradeService gradeService;

    @Autowired
    private TokenUtil tokenUtil;


    @Autowired
    private RandomUtil randomUtil;


    @Override
    public JsonResult<Student> getStudent(Long studentId) {
        Student student = studentMapper.getStudent(studentId);
        if (student == null) {
            return new JsonResult<>("学生信息不存在", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", student, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentMapper.deleteStudent(studentId);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    public List<Student> getClassStudent(Integer studentClassId) {
        return studentMapper.getClassStudent(studentClassId);
    }

    @Override
    public List<Student> getAcademyStudent(Integer academyId) {
        return studentMapper.getAcademyStudent(academyId);
    }

    @Override
    public JsonResult studentLogin(Student student) {
        Student tempStudent = this.getStudent(student.getStudentId()).getData();
        if (tempStudent == null) {
            return new JsonResult("学生信息不存在", ResultCode.NOT_FOUND.getCode(), false);
        }
        logger.info("学生登录：" + student);
        if (tempStudent.getStudentPassword().equals(student.getStudentPassword())) {
            Map<String, String> map = new HashMap<>(3);
            map.put(TokenConfig.LOGIN_ID, Long.toString(student.getStudentId()));
            map.put(TokenConfig.LOGIN_TIME, Long.toString(new Date().getTime()));
            map.put(TokenConfig.ANSWER_INDEX, Integer.toString(tempStudent.getStudentAnswerCount() + 1));
            try {
                logger.info("登录成功:" + student.getStudentId());
                if (tempStudent.getStudentAnswerCount() > 0) {
                    //这是第一次答题机会已经使用完了的第二次登陆
                    return new JsonResult<>("登录成功", tokenUtil.getToken(map), ResultCode.ILLEGALITY.getCode(), true);
                }
                return new JsonResult<>("登录成功", tokenUtil.getToken(map), ResultCode.SUCCESS.getCode(), true);
            } catch (BaseException e) {
                logger.info("token生成失败:" + e.toString());
                return new JsonResult("请求错误,请稍后再试", ResultCode.ILLEGALITY.getCode(), false);
            }
        }
        logger.info("登录失败：" + student.getStudentId());
        return new JsonResult("账号或密码错误", ResultCode.FAIL.getCode(), false);
    }


    @Override
    public int getStudentGradeIndex(Long studentId) {
        Student stu = getStudent(studentId).getData();
        return stu.getStudentAnswerCount();
    }

    @Override
    public JsonResult calStudentScore(String[] radioAnswer, int[] radioTopics,
                                      String[] multipartAnswer, int[] multipartTopics,
                                      String[] judgeAnswer, int[] judgeTopicNumbers,
                                      Student student, long time) throws BaseException {
        //判断token的有效答题次数
        Grade grade = new Grade();
        grade.setStudentGradeIndex(student.getStudentAnswerCount());
        grade.setStudentId(student.getStudentId());
        //本次token的提交次数已经使用完了
        if (gradeService.getStudentGradeTime(grade) != null) {
            logger.info("重复提交答案，请求拦截!" + student);
            return new JsonResult("你本次已经答题完成", ResultCode.ILLEGALITY.getCode(), false);
        }
        int allScore = 0;
        try {
            int judgeScore = judgeTopicService.getJudgeScore(judgeAnswer, judgeTopicNumbers);
            int radioScore = radioTopicService.getRadioScore(radioAnswer, radioTopics);
            int multipartScore = multipartTopicService.getMultipartScore(multipartAnswer, multipartTopics);
            logger.info("判断得分：" + judgeScore + " 单选得分：" + radioScore + " 多选得分：" + multipartScore);
            allScore = judgeScore + radioScore + multipartScore;
        } catch (BaseException e) {
            logger.info("学生答案分数计算失败 : " + student);
            throw new NoSupportException(e.getMessage());
        }
        //设置学生分数
        grade.setStudentGrade(allScore);
        //存秒
        grade.setStudentSpendTime((System.currentTimeMillis() - time) / 1000);
        //更新学生分数
        logger.info("计算学生的分数完成:" + allScore);
        //检查学生信息是否存在
        Student tempStudent = this.getStudent(student.getStudentId()).getData();
        if (tempStudent == null) {
            return new JsonResult("学生不存在", ResultCode.ILLEGALITY.getCode(), false);
        }
        logger.info("开始更新数据");
        //本次答题次数小于等于最大答题次数
        if (student.getStudentAnswerCount() > 0 && student.getStudentAnswerCount() <= QuestionConfig.MAX_ANSWER_COUNT) {
            //插入学生的成绩
            grade.setStudentGradeIndex(student.getStudentAnswerCount());
            grade.setStudentId(student.getStudentId());
            try {
                gradeService.insertGrade(grade);
            } catch (DuplicateKeyException e) {
                logger.info("成绩发生主键冲突:" + e);
                throw new NoSupportException("答题失败");
            }
            //更新学生的答题次数
            logger.info("更新学生答题次数");
            tempStudent = new Student();
            tempStudent.setStudentId(student.getStudentId());
            tempStudent.setStudentAnswerCount(1);
            this.updateStudent(tempStudent);
        }
        return new JsonResult<>("成绩提交成功", allScore, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult getNextToken(Student student) {
        Student tempStudent = this.getStudent(student.getStudentId()).getData();
        logger.info("学生获取第二次答题机会");
        if (tempStudent == null) {
            logger.info("学生信息不存在");
            return new JsonResult("数据不存在", ResultCode.NOT_FOUND.getCode(), false);
        }
        if (tempStudent.getStudentAnswerCount() >= QuestionConfig.MAX_ANSWER_COUNT) {
            logger.info("学生答题次数达到上限");
            return new JsonResult("你答题的次数已用完", ResultCode.FAIL.getCode(), false);
        }
        Map<String, String> map = new HashMap<>(3);
        map.put(TokenConfig.LOGIN_TIME, Long.toString(new Date().getTime()));
        map.put(TokenConfig.LOGIN_ID, Long.toString(student.getStudentId()));
        map.put(TokenConfig.ANSWER_INDEX, Integer.toString(tempStudent.getStudentAnswerCount() + 1));
        try {
            String token = tokenUtil.getToken(map);
            logger.info("token获取成功");
            return new JsonResult<>("获取成功", token, ResultCode.SUCCESS.getCode(), true);
        } catch (BaseException e) {
            logger.info("获取第二次答题token成功");
            logger.info("生成token失败");
        }
        return new JsonResult("获取失败", ResultCode.FAIL.getCode(), true);
    }

    @Override
    public JsonResult<BaseTopic> getTopic(Long studentId, Integer topicIndex, String token) {
        if (studentId != null) {
            Student student = getStudent(studentId).getData();
            if (student == null) {
                logger.info("用户存在:" + studentId);
                return new JsonResult<>("用户不存在", ResultCode.NOT_FOUND.getCode(), false);
            }
            //判断这个token是不是已经提交过了
            String answerIndexStr = tokenUtil.getParamByToken(token, TokenConfig.ANSWER_INDEX);
            Integer answerIndex = Integer.parseInt(answerIndexStr);
            Grade grade = new Grade();
            grade.setStudentId(studentId);
            grade.setStudentGradeIndex(answerIndex);
            if (gradeService.getStudentGradeTime(grade) != null) {
                logger.info("答题完成");
                return new JsonResult<>("你已经答题完成", null, ResultCode.ILLEGALITY.getCode(), false);
            }
        }
        BaseTopic topic = null;
        if (topicIndex > 0 && topicIndex <= QuestionConfig.RADIO_TOPIC_NUM) {
            int index = randomUtil.randomStepNum(QuestionConfig.RADIO_TOPIC_TOTAL_NUM, QuestionConfig.RADIO_TOPIC_NUM, topicIndex);
            topic = radioTopicService.getRadioTopic(index);
            topic.setTopicType(1);
        } else if (topicIndex > QuestionConfig.RADIO_TOPIC_NUM && topicIndex <= (QuestionConfig.RADIO_TOPIC_NUM + QuestionConfig.MULTIPART_TOPIC_NUM)) {
            int index = randomUtil.randomStepNum(QuestionConfig.MULTIPART_TOPIC_TOTAL_NUM, QuestionConfig.MULTIPART_TOPIC_NUM, (topicIndex - QuestionConfig.RADIO_TOPIC_NUM));
            topic = multipartTopicService.getMultipartTopic(index);
            topic.setTopicType(3);
        } else if (topicIndex > (QuestionConfig.RADIO_TOPIC_NUM + QuestionConfig.MULTIPART_TOPIC_NUM) && topicIndex <= (QuestionConfig.MULTIPART_TOPIC_NUM + QuestionConfig.RADIO_TOPIC_NUM + QuestionConfig.JUDGE_TOPIC_NUM)) {
            int index = randomUtil.randomStepNum(QuestionConfig.JUDGE_TOPIC_TOTAL_NUM, QuestionConfig.JUDGE_TOPIC_NUM, (topicIndex - QuestionConfig.RADIO_TOPIC_NUM - QuestionConfig.MULTIPART_TOPIC_NUM));
            topic = judgeTopicService.getJudgeTopic(index);
            topic.setTopicType(2);
        }
        logger.info(studentId + "获取题目信息" + topicIndex + ",得到" + topic);
        return new JsonResult<>("获取成功", topic, ResultCode.SUCCESS.getCode(), true);
    }


}
