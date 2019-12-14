package com.qt.question.dao.mapper;

import com.qt.question.pojo.StudentClass;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 *
 */
public interface StudentClassMapper {

    /**
     * 插入班级信息
     *
     * @param studentClass 班级对象
     */
    int insertStudentClass(StudentClass studentClass);

    /**
     * 更新班级数据
     *
     * @param studentClass 班级对象
     */
    void updateStudentClass(StudentClass studentClass);


    /**
     * 删除班级
     *
     * @param studentClassId 班级id
     */
    void deleteStudentClass(Integer studentClassId);

    /**
     * 获得一个班级对象
     *
     * @param studentClassId 班级id
     * @return StudentClass
     */
    StudentClass getStudentClass(Integer studentClassId);
}
