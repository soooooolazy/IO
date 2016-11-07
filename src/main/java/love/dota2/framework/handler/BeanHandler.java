package love.dota2.framework.handler;

import love.dota2.framework.utils.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClassHandler获取所有的class对象
 */
public final class BeanHandler {
    /**
     * Bean Map，存放Bean类与Bean实例
     */
    private static final Map<Class<?>,Object> BEAN_MAP=new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet=ClassHandler.getBeanClassSet();
        for (Class<?> beanClass:beanClassSet){
            Object obj= ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /**
     * 获取bean map
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     */
    public static <T> T getBean(Class<T> clazz){
        if(!BEAN_MAP.containsKey(clazz)){
            throw new RuntimeException("can not find bean by class:"+clazz);
        }
        return (T)BEAN_MAP.get(clazz);
    }
}
