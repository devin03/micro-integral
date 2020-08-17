package com.devin.micro.integral.extension.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 文件名生成工具类
 * 按照时间戳和随机字符串生成文件的名字
 * @author wangdongming
 * @date 2020/08/16
 */
public class FileCreateNameUtils {


    public static final String numberChar = "0123456789";

    /**
     * 文件名生成工具类
     * @author wangdongming
     * @date 2020/08/16
     */
    public static String toCreateName() {
        return getNowDateToString() + generateNum(10);
    }

    /**
     * 生成日期字符串 yyyyMMddHHmm
     * @author wangdongming
     * @date 2020/08/16
     */
    public static String getNowDateToString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 生成随机数
     * @author wangdongming
     * @date 2020/08/16
     */
    public static String generateNum(int len) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return sb.toString();
    }
}