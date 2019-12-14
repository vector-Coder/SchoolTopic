package com.qt.question.pojo;

/**
 * @author vector
 * @since 2019/10/9
 *
 * 单选对象
 */
public class RadioTopic extends BaseTopic{
    //选项A
    private String  topicOptionA;
    //选项B
    private String  topicOptionB;
    //选项C
    private String  topicOptionC;
    //选项D
    private String  topicOptionD;

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


    @Override
    public String toString() {
        return "RadioTopic{" +
                "topicOptionA='" + topicOptionA + '\'' +
                ", topicOptionB='" + topicOptionB + '\'' +
                ", topicOptionC='" + topicOptionC + '\'' +
                ", topicOptionD='" + topicOptionD + '\'' + super.toString() +
                '}';
    }
}
