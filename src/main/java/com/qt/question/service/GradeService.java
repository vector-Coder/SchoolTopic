package com.qt.question.service;

import com.qt.question.pojo.Grade;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/27
 * @Desc ...
 * @Since 1.0.0
 */
public interface GradeService {


    boolean insertGrade(Grade grade);

    boolean deleteGrade(Grade grade);

    boolean updateGrade(Grade grade);

    List<Grade> getStudentGrade(Integer studentId);

    Grade getStudentGradeTime(Grade grade);
}
