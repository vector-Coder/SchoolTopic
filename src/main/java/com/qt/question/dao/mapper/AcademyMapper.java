package com.qt.question.dao.mapper;

import com.qt.question.pojo.Academy;

/**
 * @author vector
 * @since 2019/10/10
 * <p>
 *
 */

public interface AcademyMapper {

    /**
     * 查询学院信息
     *
     * @param academyId 学院id
     * @return Academy
     */
    Academy getAcademyById(Integer academyId);

    /**
     * 插入一个学院的信息
     *
     * @param academy 学院信息
     */
    int insertAcademy(Academy academy);

    /**
     * 删除一个学院的信息
     *
     * @param academyId 学院id
     */
    void deleteAcademy(Integer academyId);

    /**
     * 更新学院的学院的数据
     *
     * @param academy 学院对象
     */
    void updateAcademy(Academy academy);
}
