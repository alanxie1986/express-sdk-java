package com.exp.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }
    /**
     * 获得一个[0,max)之间的随机整数。
     * @param max
     * @return
     */
    public static int getRandomInt(int max) {
        return getRandom().nextInt(max);
    }

    /**
     * 获得一个[min, max]之间的随机整数
     * @param min
     * @param max
     * @return
     */
    public static int getRandomInt(int min, int max) {
        return getRandom().nextInt(max-min+1) + min;
    }

    /**
     * 获得一个[0,max)之间的长整数。
     * @param max
     * @return
     */
    public static long getRandomLong(long max) {
        return getRandom().nextLong(max);
    }

    /**
     * 生成一个n位的随机数，用于验证码等
     * @param n
     * @return
     */
    public static String getRandNumber(int n) {
        String rn = "";
        if (n > 0 && n < 10) {
            //Random r = new Random();
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < n; i++) {
                str.append('9');
            }
            int num = Integer.parseInt(str.toString());
            while (rn.length() < n) {
                rn = String.valueOf(getRandomInt(num));
            }
        } else {
            rn = "0";
        }
        return rn;
    }

    private final static char[] randomChars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N',   'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'X',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };

    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            int randomNumber = getRandomInt(randomChars.length);
            stringBuilder.append(randomChars[randomNumber]);
        }
        return stringBuilder.toString();
    }

}
