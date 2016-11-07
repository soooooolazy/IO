package love.dota2.framework;

import love.dota2.framework.annotation.Controller;
import love.dota2.framework.bean.Handler;
import love.dota2.framework.bean.Param;
import love.dota2.framework.bean.Plain;
import love.dota2.framework.bean.View;
import love.dota2.framework.handler.BeanHandler;
import love.dota2.framework.handler.ControllerHandler;
import love.dota2.framework.utils.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发，处理HttpServletRequest和HttpServletResponse
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化Handler
        HandlerLoader.init();
        //获取ServletContext(用于注册ServletContext)
        ServletContext servletContext=servletConfig.getServletContext();
        //处理JSP
        ServletRegistration jspServlet=servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigLoader.getAppJspPath()+"*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet=servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigLoader.getAppStaticPath()+"*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法与请求路径
        String requestMethod= req.getMethod().toLowerCase();
        String requestUrl=req.getPathInfo();
        //获取RequestMapping的处理器
        Handler handler=ControllerHandler.getHandler(requestMethod,requestUrl);
        if(handler!=null){
            //获取Controller类
            Class<?> controllerClass=handler.getControllerClass();
            Object controllerBean= BeanHandler.getBean(controllerClass);
            //请求参数对象
            Map<String,Object> paramMap=new HashMap<String,Object>();
            Enumeration<String> paramNames=req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName=paramNames.nextElement();
                String paramValue=req.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body= CodeUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if(StringUtil.isNotEmpty(body)){
                String[] params= StringUtil.splitString(body,"&");
                if(ArrayUtil.isNotEmpty(params)){
                    for(String param:params){
                        String[] array=StringUtil.splitString(param,"=");
                        if(ArrayUtil.isNotEmpty(array)){
                            String paramName=array[0];
                            String paramValue=array[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }
            Param param=new Param(paramMap);
            //调用RequestMapping方法
            Method method=handler.getActionMethod();
            Object result= ReflectionUtil.invokeMethod(controllerBean,method,param);
            //处理RequestMapping返回值
            if(result instanceof View){
                View view=(View)result;
                String path=view.getPath();
                if(StringUtil.isNotEmpty(path)){
                    if(path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath()+path);
                    }else {
                        Map<String,Object> model=view.getModel();
                        for(Map.Entry<String,Object>entry:model.entrySet()){
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigLoader.getAppJspPath()+path).forward(req,resp);
                    }
                }
            }else if(result instanceof Plain){
                Plain plain= (Plain) result;
                Object model=plain.getModel();
                if(model!=null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer=resp.getWriter();
                    String json=JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }

    }
}
