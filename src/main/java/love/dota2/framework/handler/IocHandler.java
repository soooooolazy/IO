package love.dota2.framework.handler;


import love.dota2.framework.annotation.Wire;
import love.dota2.framework.utils.ArrayUtil;
import love.dota2.framework.utils.CollectionUtil;
import love.dota2.framework.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * IOC(Inversion Of Controll)的简单实现
 * Ioc容器中的对象都是单例
 *
 */
public final class IocHandler {
    static {
        Map<Class<?>, Object> beanMap=BeanHandler.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
                //获得Class对象和实例对象
                Class<?> beanClass=beanEntry.getKey();
                Object beanInstance=beanEntry.getValue();
                //获取所有成员变量
                Field[] fields=beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(fields)){
                    for(Field field:fields){
                        //判断是否有Wire注解
                        if(field.isAnnotationPresent(Wire.class)){
                            //在beanMap中获取field的实例
                            Class<?> fieldClass=field.getType();
                            Object fieldInstance=beanMap.get(fieldClass);
                            if(fieldInstance!=null){
                                ReflectionUtil.setField(beanInstance,field,fieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
