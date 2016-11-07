package love.dota2.framework.utils;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

public class CollectionUtil {
    /*
        判断Collection是否为空
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    public  static  boolean isNotEmpty(Collection<?> collection){
        return  !CollectionUtils.isEmpty(collection);
    }

    /*
        判断Map是否为空
     */
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }
    public static boolean isNotEmpty(Map<?,?> map){
        return  !MapUtils.isEmpty(map);
    }
}
