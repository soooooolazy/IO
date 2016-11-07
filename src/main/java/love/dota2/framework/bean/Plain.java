package love.dota2.framework.bean;

/**
 * Plain对象，返回JSON数据
 */
public class Plain {
    private Object model;

    private Plain(Object model) {
        this.model = model;
    }

    //将对象写入HttpServletResponse中，返回JSON数据
    public Object getModel() {
        return model;
    }
}
