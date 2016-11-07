package love.dota2.framework.bean;

import love.dota2.framework.utils.CastUtils;

import java.util.Map;

/**
 * HttpServletRequest
 * 请求参数对象
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String,Object> paramMap){
        this.paramMap=paramMap;
    }


    /**
     * 获取long型参数
     */
    public long getLong(String name){
        return CastUtils.castLong(paramMap.get(name));
    }

    public Map<String,Object> getMap(){
        return paramMap;
    }
}
