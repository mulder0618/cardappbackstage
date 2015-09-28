package com.zhx.base.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by admin on 14-7-15.
 */
public class PropertiesUtil {
    private static Properties p = new Properties();
        /**
         * 读取properties配置文件信息
         */
    static{
        try {
            p.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("systemconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据key得到value的值
     */
    public static String getValue(String key) {
        return p.getProperty(key);
    }


}
