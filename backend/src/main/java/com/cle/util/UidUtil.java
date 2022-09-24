package com.cle.util;

import java.util.UUID;

public class UidUtil {
    /**
     * 随机获取UUID（常用于唯一识别，如ID生成）
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取x内的随机一位数（包含x）
     */
    public static int getInt(int x) {
        if (x <= 0) {
            return 0;
        }
        return (int) (Math.random() * (x + 1));
    }

    /**
     * 获取x到y内的随机一位数（包含x,y）
     */
    public static int getInt(int x, int y) {
        if (x <= 0 || y <= 0 || x > y) {
            return 0;
        }
        return (int) (Math.random() * (y - x + 1)) + x;
    }

    /**
     * 随机获取x位字符串（只包含小写字母，常用于区分不同事物）
     */
    public static String getLetterString(int x) {
        if (x <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append((char) (int) (Math.random() * 26 + 97));
        }
        return sb.toString();
    }

    /**
     * 随机获取x位字符串（只包含数字，常用于验证码）
     */
    public static String getNumberString(int x) {
        if (x <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }
}
