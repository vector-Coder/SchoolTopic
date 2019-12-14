package com.qt.question.util.string.impl;

import com.qt.question.util.string.StringUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StringUtilImpl implements StringUtil {

    @Override
    public String[] splitAnswer(String answer) {
        String[] answers = answer.split("\\d+");
        String[] _answers = new String[answers.length - 1];
        for (int i = 1; i < answers.length; i++)
            _answers[i - 1] = answers[i];
        return _answers;
    }

    @Override
    public int[] splitTopicNumber(String answer) {
        String[] number = answer.split("[A-Z√×]+|#");
        int[] _number = new int[number.length];
        for (int i = 0; i < number.length; i++) {
            _number[i] = Integer.parseInt(number[i]);
        }
        return _number;
    }

    @Override
    public String sortMultipartAnswer(String multipartAnswer) {
        char[] array = multipartAnswer.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }




    public static void main(String[] args) {
        StringUtilImpl s = new StringUtilImpl();
        String str = "1A2D4C6#10C111DCA";
        int[] number = s.splitTopicNumber(str);
        String[] answer = s.splitAnswer(str);
        for(int i=0;i<number.length;i++)
            System.out.println(number[i] +"--"+answer[i]);
    }
}
