package love.dota2.framework;


import love.dota2.framework.utils.PropsUtil;

import java.util.Properties;

public class ConfigLoader {
    private static final Properties PROPERTIES=PropsUtil.loadProps(ConfigConstant.CONFIG_XML);


    public static  String getDriver(){
        return PropsUtil.getString(PROPERTIES,ConfigConstant.JDBC_DRIVER);
    }

    public static String getUrl(){
        return PropsUtil.getString(PROPERTIES,ConfigConstant.JDBC_URL);
    }

    public static String getUsername(){
        return PropsUtil.getString(PROPERTIES,ConfigConstant.JDBC_USERNAME);
    }

    public static String getPassword(){
        return PropsUtil.getString(PROPERTIES,ConfigConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePackage(){
        return PropsUtil.getString(PROPERTIES,ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppJspPath(){
        return PropsUtil.getString(PROPERTIES,ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppStaticPath(){
        return PropsUtil.getString(PROPERTIES,ConfigConstant.APP_STATIC_PATH);
    }
}
