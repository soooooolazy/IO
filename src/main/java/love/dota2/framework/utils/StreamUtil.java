package love.dota2.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作类
 */
public final class StreamUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(StreamUtil.class);

    /**
     * get String from InputStream
     */
    public static String getString(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line=br.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch(IOException e){
            LOGGER.error("get String failed",e);
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
