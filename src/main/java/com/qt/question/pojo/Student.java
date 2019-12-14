package com.qt.question.pojo;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author vector
 * @since 2019/10/9
 * <p>
 * 学生对象
 */
public class Student {

    //学生id
    @NotNull
    private Long studentId;
    //学生姓名
    private String studentName;
    //学生学院
    private Integer studentAcademy;
    //学生班级
    private Integer studentClass;
    //答题次数
    private Integer studentAnswerCount;
    //学生性别
    private String studentGender;
    //学生专业
    private String studentMajor;
    //学生名族
    private String studentNation;
    //学生的登录密码
    @NotNull
    private String studentPassword;

    private Academy academy;

    private StudentClass stuClass;

    private List<Grade> gradeList;

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public Integer getStudentAnswerCount() {
        return studentAnswerCount;
    }

    public void setStudentAnswerCount(Integer studentAnswerCount) {
        this.studentAnswerCount = studentAnswerCount;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public StudentClass getStuClass() {
        return stuClass;
    }

    public void setStuClass(StudentClass stuClass) {
        this.stuClass = stuClass;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentNation() {
        return studentNation;
    }

    public void setStudentNation(String studentNation) {
        this.studentNation = studentNation;
    }


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAcademy() {
        return studentAcademy;
    }

    public void setStudentAcademy(Integer studentAcademy) {
        this.studentAcademy = studentAcademy;
    }

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Integer studentClass) {
        this.studentClass = studentClass;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAcademy=" + studentAcademy +
                ", studentClass=" + studentClass +
                ", studentAnswerCount=" + studentAnswerCount +
                ", studentGender='" + studentGender + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentNation='" + studentNation + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", academy=" + academy +
                ", stuClass=" + stuClass +
                ", gradeList=" + gradeList +
                '}';
    }
}
