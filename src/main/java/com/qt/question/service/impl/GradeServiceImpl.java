package com.qt.question.service.impl;

import com.qt.question.dao.mapper.GradeMapper;
import com.qt.question.pojo.Grade;
import com.qt.question.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/27
 * @Desc ...
 * @Since 1.0.0
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public boolean insertGrade(Grade grade) {
        return gradeMapper.insertGrade(grade) > 0;
    }

    @Override
    public boolean deleteGrade(Grade grade) {
        return gradeMapper.deleteGrade(grade) > 0;
    }

    @Override
    public boolean updateGrade(Grade grade) {
        return gradeMapper.updateGrade(grade) > 0;
    }

    @Override
    public List<Grade> getStudentGrade(Integer studentId) {
        return gradeMapper.getStudentGrade(studentId);
    }

    @Override
    public Grade getStudentGradeTime(Grade grade) {
        return gradeMapper.getStudentGradeTime(grade);
    }
}
