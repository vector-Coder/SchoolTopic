package com.qt.question.service.impl;

import com.qt.question.dao.mapper.AcademyMapper;
import com.qt.question.dao.mapper.StudentMapper;
import com.qt.question.pojo.Academy;
import com.qt.question.pojo.Student;
import com.qt.question.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "academyService")
public class AcademyServiceImpl implements AcademyService {

    @Autowired
    private AcademyMapper academyMapper;

    @Override
    public int insertAcademy(Academy academy) {
        int res = academyMapper.insertAcademy(academy);
        if(res > 0){
            return academy.getAcademyId();
        }
        return -1;
    }
}
