package com.qt.question.pojo;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Objects;

/**
 * @author vector
 * @since 2019/10/9
 *
 * 学院对象
 */
public class Academy {

    //学院id
    private Integer academyId;
    //学院的名称
    private String academyName;
    //学院的参加人数
    private Integer academyJoinCount;
    //学院的总分
    private Integer academyGradeSum;
    //学院的平均时间
    private Double academyTimeSum;

    //学生人数
    private int academyStudentCount;

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public int getAcademyStudentCount() {
        return academyStudentCount;
    }

    public void setAcademyStudentCount(int academyStudentCount) {
        this.academyStudentCount = academyStudentCount;
    }


    public int getAcademyJoinCount() {
        return academyJoinCount;
    }

    public void setAcademyJoinCount(int academyJoinCount) {
        this.academyJoinCount = academyJoinCount;
    }


    public int getAcademyGradeSum() {
        return academyGradeSum;
    }

    public void setAcademyGradeSum(int academyGradeSum) {
        this.academyGradeSum = academyGradeSum;
    }

    public Double getAcademyTimeSum() {
        return academyTimeSum;
    }

    public void setAcademyTimeSum(Double academyTimeSum) {
        this.academyTimeSum = academyTimeSum;
    }

    @Override
    public String toString() {
        return "Academy{" +
                "academyId=" + academyId +
                ", academyName='" + academyName + '\'' +
                ", academyJoinCount=" + academyJoinCount +
                ", academyGradeSum=" + academyGradeSum +
                ", academyTimeSum=" + academyTimeSum +
                ", academyStudentCount=" + academyStudentCount +
                '}';
    }
}
