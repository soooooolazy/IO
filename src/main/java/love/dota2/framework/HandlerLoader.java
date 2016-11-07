package love.dota2.framework;


import love.dota2.framework.handler.BeanHandler;
import love.dota2.framework.handler.ClassHandler;
import love.dota2.framework.handler.ControllerHandler;
import love.dota2.framework.handler.IocHandler;
import love.dota2.framework.utils.ClassLoaderUtil;

public final class HandlerLoader {
    public static void init(){
        Class<?>[] list={ClassHandler.class, BeanHandler.class, ControllerHandler.class, IocHandler.class};
        for(Class<?> clazz:list){
            ClassLoaderUtil.loadClass(clazz.getName());
        }
    }
}
