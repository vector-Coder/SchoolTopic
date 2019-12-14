package com.qt.question.web.controller;


import com.qt.question.config.TokenConfig;
import com.qt.question.exception.BaseException;
import com.qt.question.pojo.Academy;
import com.qt.question.pojo.Student;
import com.qt.question.pojo.StudentClass;
import com.qt.question.service.AcademyService;
import com.qt.question.service.StudentClassService;
import com.qt.question.service.StudentService;
import com.qt.question.util.JsonResult;
import com.qt.question.util.Result;
import com.qt.question.util.ResultCode;
import com.qt.question.util.token.TokenUtil;
import org.apache.poi.xwpf.usermodel.TOC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/Student")
@Validated
public class StudentController {

    //日志打印工具
    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private StudentService studentService;


    /**
     * 学生登录答题的方法
     *
     * @return Result
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public JsonResult login(@Valid Student student) {
        //判断该用户是否存在
        if (student == null) {
            return new JsonResult("参数错误", ResultCode.ILLEGALITY.getCode(), false);
        }
        return studentService.studentLogin(student);
    }


    @GetMapping("/getNextToken")
    private JsonResult getNextToken(String token){
        String studentId = tokenUtil.getParamByToken(token,TokenConfig.LOGIN_ID);
        Student student = new Student();
        try{
            student.setStudentId(Long.parseLong(studentId));
            return studentService.getNextToken(student);
        }catch (Exception e){
            return new JsonResult("获取失败",ResultCode.FAIL.getCode(),false);
        }
    }


    /**
     * 获取学生的成绩
     *
     * @param token 令牌
     * @return Result
     */
    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    public JsonResult getStudentById(@NotNull(message = "身份凭证不可为空") String token) {
        Long studentId;
        try {
            studentId = Long.parseLong(tokenUtil.getParamByToken(token, TokenConfig.LOGIN_ID));
        } catch (Exception e) {
            return new JsonResult("获取失败", ResultCode.FAIL.getCode(), false);
        }
        return studentService.getStudent(studentId);
    }

}
