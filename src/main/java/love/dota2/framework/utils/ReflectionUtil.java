package love.dota2.framework.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class ReflectionUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     */
    public static Object newInstance(Class<?> clazz){
        Object instance;
        try {
            instance=clazz.newInstance();
        }catch (Exception e){
            LOGGER.error("new instance failed",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     */
    public static Object invokeMethod(Object obj, Method method, Object... objects){
        Object result;
        try {
            method.setAccessible(true);
            result=method.invoke(obj,objects);
        }catch (Exception e){
            LOGGER.error("invoke method failed",e);
            throw new RuntimeException(e);
        }
        return  result;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        }catch (Exception e){
            LOGGER.error("set filed failed",e);
            throw new RuntimeException(e);
        }
    }
}
