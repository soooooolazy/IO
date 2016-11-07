package love.dota2.framework.utils;

import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码与解码工具类
 */
public final class CodeUtil {
    private final static Logger LOGGER= LoggerFactory.getLogger(CodeUtil.class);

    /**
     * URL编码
     */
    public static String encodeURL(String url){
        String target;
        try {
            target= URLEncoder.encode(url,"UTF-8");
        }catch (Exception e){
            LOGGER.error("URL encode failed",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * URL解码
     */
    public static String decodeURL(String url){
        String target;
        try{
            target= URLDecoder.decode(url,"UTF-8");
        }catch(Exception e){
            LOGGER.error("URL decode failed",e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
