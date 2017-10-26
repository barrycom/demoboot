package com.xe.demo.common.utils;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by Administrator on 2017-10-24.
 */
public class CheckUtil {

    /**
     * 验证是否有空值的参数，只要有一个，就返回true
     *
     * @param args
     * @return true
     */
    public static boolean checkNulls(String... args) {

        if (args.length == 0) {
            return true;
        }
        //
        for (String str : args) {
            if (isNullOrEmpty(str)) {
                return true;
            }
        }
        return false;
    }
}