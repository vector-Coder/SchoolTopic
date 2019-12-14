package com.qt.question.pojo;

/**
 * @author vector
 * @since 2019/10/9
 *
 * 多选题对象
 */
public class MultipartTopic extends BaseTopic {
    //选项A
    private String  topicOptionA;
    //选项B
    private String  topicOptionB;
    //选项C
    private String  topicOptionC;
    //选项D
    private String  topicOptionD;
    //选项E
    private String  topicOptionE;

    public String getTopicOptionA() {
        return topicOptionA;
    }

    public void setTopicOptionA(String topicOptionA) {
        this.topicOptionA = topicOptionA;
    }

    public String getTopicOptionB() {
        return topicOptionB;
    }

    public void setTopicOptionB(String topicOptionB) {
        this.topicOptionB = topicOptionB;
    }

    public String getTopicOptionC() {
        return topicOptionC;
    }

    public void setTopicOptionC(String topicOptionC) {
        this.topicOptionC = topicOptionC;
    }

    public String getTopicOptionD() {
        return topicOptionD;
    }

    public void setTopicOptionD(String topicOptionD) {
        this.topicOptionD = topicOptionD;
    }

    public String getTopicOptionE() {
        return topicOptionE;
    }

    public void setTopicOptionE(String topicOptionE) {
        this.topicOptionE = topicOptionE;
    }

    @Override
    public String toString() {
        return "MultipartTopic{" +
                "topicOptionA='" + topicOptionA + '\'' +
                ", topicOptionB='" + topicOptionB + '\'' +
                ", topicOptionC='" + topicOptionC + '\'' +
                ", topicOptionD='" + topicOptionD + '\'' +
                ", topicOptionE='" + topicOptionE + '\'' + super.toString()  +
                '}';
    }
}
