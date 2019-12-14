package com.qt.question.util.random.impl;

import com.qt.question.util.random.RandomUtil;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author vector
 * @since 2019/10/9
 *
 * 随机数对象
 */
@Component
public class RandomUtilImpl implements RandomUtil {

    private static  Random random;

    static {
        random = new Random();
    }

    @Override
    public int randomStepNum(int total, int time, int index) {
        return random.nextInt(total / time) + (total / time) * (index - 1);
    }
}
