package love.dota2.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * View对象，则为JSP页面
 */
public class View {
    /**
     * 视图地址
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String,Object> model;

    public View(String path){
        this.path=path;
        model=new HashMap<String,Object>();
    }

    public View addModel(String key,String value){
        model.put(key,value);
        return this;
    }

    public String getPath(){
        return path;
    }
    public Map<String,Object> getModel(){
        return model;
    }
}
