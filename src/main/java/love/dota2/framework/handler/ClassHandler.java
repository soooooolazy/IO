package love.dota2.framework.handler;


import love.dota2.framework.ConfigLoader;
import love.dota2.framework.annotation.Controller;
import love.dota2.framework.annotation.Service;
import love.dota2.framework.utils.ClassLoaderUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 获取不同包下的Set<Class<?>>
 */
public final class ClassHandler {
    private static  final Set<Class<?>> CLASS_SET;

    static {
        CLASS_SET= ClassLoaderUtil.getClassSet(ConfigLoader.getAppBasePackage());
    }

    /**
     * 获取base_package包下的所有class set
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取所有Service注解的类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> cLassSet=new HashSet<Class<?>>();
        for(Class<?> clazz:CLASS_SET){
            if(clazz.isAnnotationPresent(Service.class)){
                cLassSet.add(clazz);
            }
        }
        return cLassSet;
    }

    /**
     * 获取所有Controller注解的类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?> clazz:CLASS_SET){
            if(clazz.isAnnotationPresent(Controller.class)){
                classSet.add(clazz);
            }
        }
        return  classSet;
    }

    /**
     * 容器实现，获得所有的Service,Controller类
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet =new HashSet<Class<?>>();
        beanClassSet.addAll(getControllerClassSet());
        beanClassSet.addAll(getServiceClassSet());
        return beanClassSet;
    }
}
