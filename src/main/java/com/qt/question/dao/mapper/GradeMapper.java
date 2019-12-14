package com.qt.question.dao.mapper;

import com.qt.question.pojo.Grade;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/27
 * @Desc ...
 * @Since 1.0.0
 */
public interface GradeMapper {

    int insertGrade(Grade grade);

    int deleteGrade(Grade grade);

    int updateGrade(Grade grade);

    List<Grade> getStudentGrade(Integer studentId);

    Grade getStudentGradeTime(Grade grade);
}
