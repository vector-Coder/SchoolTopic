package com.qt.question.pojo;

/**
 * @author vector
 * @since 2019/10/9
 *
 * 班级信息
 */
public class StudentClass {
    //班级id
    private Integer classId;
    //班级名称
    private String className;
    //班级学院信息
    private Integer classAcademyId;
    //班级的参加的人数
    private Integer classJoinCount;
    //班级总分
    private  Integer classGradeSum;
    //班级的平均时间
    private Double classTimeSum;
    //学生人数
    private Integer classStudentCount;



    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassAcademyId() {
        return classAcademyId;
    }

    public void setClassAcademyId(Integer classAcademyId) {
        this.classAcademyId = classAcademyId;
    }

    public int getClassJoinCount() {
        return classJoinCount;
    }

    public void setClassJoinCount(int classJoinCount) {
        this.classJoinCount = classJoinCount;
    }

    public int getClassStudentCount() {
        return classStudentCount;
    }

    public void setClassStudentCount(int classStudentCount) {
        this.classStudentCount = classStudentCount;
    }

    public int getClassGradeSum() {
        return classGradeSum;
    }

    public void setClassGradeSum(int classGradeSum) {
        this.classGradeSum = classGradeSum;
    }

    public Double getClassTimeSum() {
        return classTimeSum;
    }

    public void setClassTimeSum(Double classTimeSum) {
        this.classTimeSum = classTimeSum;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classAcademyId=" + classAcademyId +
                ", classJoinCount=" + classJoinCount +
                ", classGradeSum=" + classGradeSum +
                ", classTimeSum=" + classTimeSum +
                ", classStudentCount=" + classStudentCount +
                '}';
    }
}
