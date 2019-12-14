package com.qt.question.service;

import com.qt.question.exception.BaseException;
import com.qt.question.pojo.BaseTopic;
import com.qt.question.pojo.Student;
import com.qt.question.util.JsonResult;

import java.util.List;


/**
 * @author vector
 * @since 2019/10/10
 * <p>
 */
public interface StudentService {

    /**
     * 获得一个学生的数据
     *
     * @param studentId 学生id
     * @return Student
     */
    JsonResult<Student> getStudent(Long studentId);

    /**
     * 删除一个学生对象
     *
     * @param studentId 学生id
     */
    void deleteStudent(Long studentId);

    /**
     * 更新学生信息
     *
     * @param student 学生对象
     */
    void updateStudent(Student student);

    /**
     * 插入学生信息
     *
     * @param student 学生对象
     */
    void insertStudent(Student student);

    /**
     * 获得一个班级的学生数据
     *
     * @param studentClassId 班级id
     * @return List<Student>
     */
    List<Student> getClassStudent(Integer studentClassId);

    /**
     * 获得一个学院的学生数据
     *
     * @param academyId 学院id
     * @return List<Student>
     */
    List<Student> getAcademyStudent(Integer academyId);


    JsonResult studentLogin(Student student);

    /**
     * 判断学生是否成绩
     *
     * @param studentId
     */
    int getStudentGradeIndex(Long studentId);


    JsonResult calStudentScore(String[] radioAnswer, int[] radioTopics,
                               String[] multipartAnswer, int[] multipartTopics,
                               String[] judgeAnswer, int[] judgeTopicAnswers,
                               Student student, long time) throws BaseException;


    JsonResult getNextToken(Student student);


    JsonResult<BaseTopic> getTopic(Long studentId,Integer topicIndex,String token);
}
