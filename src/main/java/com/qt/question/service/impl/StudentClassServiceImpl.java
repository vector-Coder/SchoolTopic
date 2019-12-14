package com.qt.question.service.impl;

import com.qt.question.dao.mapper.StudentClassMapper;
import com.qt.question.dao.mapper.StudentMapper;
import com.qt.question.pojo.Academy;
import com.qt.question.pojo.Grade;
import com.qt.question.pojo.Student;
import com.qt.question.pojo.StudentClass;
import com.qt.question.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "studentClassService")
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Override
    public int insertStudentClass(StudentClass studentClass) {
        int res = studentClassMapper.insertStudentClass(studentClass);
        if(res > 0){
            return studentClass.getClassId();
        }
        return -1;
    }
}
