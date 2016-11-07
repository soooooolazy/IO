package love.dota2.framework.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON工具类
 */
public final class JsonUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(JsonUtil.class);

    public static <T> String toJson(T obj){
        String json;
        json= JSON.toJSONString(obj);
        return json;
    }

    public static <T> T fromJson(String str,Class<T> type){
        T obj;
        obj= (T) JSON.parseArray(str,type);
        return obj;
    }
}
