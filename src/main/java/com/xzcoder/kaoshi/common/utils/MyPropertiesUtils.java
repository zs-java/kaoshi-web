package com.xzcoder.kaoshi.common.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * MyPropertiesUtils
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Deprecated
public class MyPropertiesUtils {

    private static Properties prop;

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    static {
        try {
            prop = new Properties();
            prop.load(MyPropertiesUtils.class.getClassLoader().getResourceAsStream("iconUpload.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
