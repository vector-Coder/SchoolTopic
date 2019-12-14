package com.qt.question.util.random;

public interface RandomUtil {

    /**
     * 随机生成一个指定段的数据
     * @param total  总共的数量
     * @param time   需要的数量
     * @param index  次数
     * @return int
     */
    int randomStepNum(int total,int time,int index);
}
