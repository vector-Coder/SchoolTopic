package com.qt.question.pojo;

/**
 * @author vector
 * @since 2019/10/9
 *
 * 题目的父类
 */
public abstract class BaseTopic {

    //题目id
    private Integer topicId;
    //题目的文本信息
    private String topicText;
    //题目的答案信息
    private String topicResult;
    //题目的类型
    private Integer topicType;

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicText() {
        return topicText;
    }

    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    public String getTopicResult() {
        return topicResult;
    }

    public void setTopicResult(String topicResult) {
        this.topicResult = topicResult;
    }

    @Override
    public String toString() {
        return "BaseTopic{" +
                "topicId=" + topicId +
                ", topicText='" + topicText + '\'' +
                ", topicResult='" + topicResult + '\'' +
                ", topicType=" + topicType +
                '}';
    }
}
