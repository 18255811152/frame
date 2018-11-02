package com.example.qwert.app.utils;

public class GeneratePassWordUtils {
    /**
     * @param length
     * @param big
     * @param small
     * @param special
     * @return
     */
    public static String getRandomPwd(int length, boolean big, boolean small, boolean special) {
        String numberString = "0123456789";
        String bigString = "ABCDEFGHIJKMLNOPQRSTUVWXYZ";
        String smallString = "abcdefghijklmnopqrstuvwxyz";
        String specialString = "~!@#$%^&*()+}{<>?[]=";
        StringBuffer sBuffer = new StringBuffer(numberString);
        if (big && small && special) {
            sBuffer = sBuffer.append(bigString).append(smallString).append(specialString);
        } else if (!big && small && special) {
            sBuffer = sBuffer.append(smallString).append(specialString);
        } else if (big & !small && special) {
            sBuffer = sBuffer.append(bigString).append(specialString);
        } else if (big & small && !special) {
            sBuffer = sBuffer.append(bigString).append(smallString);
        } else if (big & !small && !special) {
            sBuffer = sBuffer.append(bigString);
        } else if (!big & small && !special) {
            sBuffer = sBuffer.append(smallString);
        } else if (!big & !small && special) {
            sBuffer = sBuffer.append(specialString);
        } else {
            sBuffer = sBuffer;
        }

        return getRandomByString(length, sBuffer.toString());
    }

    private static String getRandomByString(int length, String str) {
        StringBuffer sb = new StringBuffer("");
        //random()方法产生的随机数在0.0和1.0之间
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt((int) Math.round(Math.random() * (str.length() - 1))));
        }
        return sb.toString();
    }

}
