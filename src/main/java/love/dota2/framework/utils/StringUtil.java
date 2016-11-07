package love.dota2.framework.utils;


import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    /*
        判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(str!=null){
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }
    /*
        是否非空
     */
    public static  boolean isNotEmpty(String str){
        return !StringUtils.isEmpty(str);
    }

    public static String[] splitString(String str,String separator){
        return StringUtils.splitByWholeSeparator(str,separator);
    }
}
