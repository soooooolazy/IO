package love.dota2.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropsUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName){
        Properties properties=null;
        InputStream is=null;
        try {
            is=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(is==null){
                throw new FileNotFoundException(fileName+"file is not fount!");
            }
            properties=new Properties();
            properties.load(is);
        }catch(IOException e){
            LOGGER.error("load properties file failed");
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failed",e);
                }
            }
        }
        return  properties;
    }

    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }

    public static String getString(Properties props,String key,String defaultValue){
        String value=defaultValue;
        if(props.containsKey(key)){
            value=props.getProperty(key);
        }
        return value;
    }
}
