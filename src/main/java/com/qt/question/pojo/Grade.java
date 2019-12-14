package com.qt.question.pojo;

/**
 * @Author Vector
 * @Date 2019/11/27
 * @Desc ...
 * @Since 1.0.0
 */
public class Grade {

    //学生ID
    private Long studentId;

    //成绩次数
    private Integer studentGradeIndex;

    //学生的成绩
    private Integer studentGrade;

    //花费的时间
    private Long studentSpendTime;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentGradeIndex() {
        return studentGradeIndex;
    }

    public void setStudentGradeIndex(Integer studentGradeIndex) {
        this.studentGradeIndex = studentGradeIndex;
    }

    public Integer getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    public Long getStudentSpendTime() {
        return studentSpendTime;
    }

    public void setStudentSpendTime(Long studentSpendTime) {
        this.studentSpendTime = studentSpendTime;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "studentId=" + studentId +
                ", studentGradeIndex=" + studentGradeIndex +
                ", studentGrade=" + studentGrade +
                ", studentSpendTime=" + studentSpendTime +
                '}';
    }
}
