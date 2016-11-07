package love.dota2.framework.handler;

import com.sun.org.apache.regexp.internal.RE;
import love.dota2.framework.annotation.RequestMapping;
import love.dota2.framework.bean.Handler;
import love.dota2.framework.bean.Request;
import love.dota2.framework.utils.ArrayUtil;
import love.dota2.framework.utils.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 对所有标记Controller注解的进行处理
 * 1.获得Request注解的方法
 * 2.获取Request注解中的表达式，获得请求方法与请求路径
 * 3.封装Request对象和Handler对象，将映射关系放入Map中
 * 4.提供可以根据请求方法与请求路径处理对象的方法
 */
public final class ControllerHandler {
    private static final Map<Request,Handler> ACTION_MAP=new HashMap<Request,Handler>();

    static {
        //获取所有的Controller
        Set<Class<?>> controllerClassSet=ClassHandler.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for(Class<?> controllerClass:controllerClassSet){
                //获取所有Method
                Method[] methods=controllerClass.getMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    //遍历方法
                    for(Method method:methods){
                        //获取Request注解
                        RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
                        String mapping=requestMapping.value();
                        //验证url
                        if(mapping.matches("\\w+:/\\w+")){
                            String[] array=mapping.split(":");
                            if(ArrayUtil.isNotEmpty(array) && array.length==2){
                                //请求方法与请求路径
                                String requestMethod=array[0];
                                String requestUrl=array[1];
                                Request request=new Request(requestMethod,requestUrl);
                                Handler handler=new Handler(controllerClass,method);
                                //初始化映射Map
                                ACTION_MAP.put(request,handler);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     */
    public static Handler getHandler(String requestMethod,String requestUrl){
        Request request=new Request(requestMethod,requestUrl);
        return ACTION_MAP.get(request);
    }
}
