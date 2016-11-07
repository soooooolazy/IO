package love.dota2.framework.utils;

/*
    转型工具类
 */
public final class CastUtils {
    /*
        转为String
     */
    public static String castString(Object obj){
        return castString(obj,"");
    }

    public static String castString(Object obj,String defaultVlue){
        return obj!=null?String.valueOf(obj):defaultVlue;
    }

    /*
        转为double
     */
    public static double castDouble(Object obj){
        return castDouble(obj,0);
    }

    public static double castDouble(Object obj,double defaultVlue){
        double doubleValue=defaultVlue;
        if(obj!=null){
            String strValue=castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    doubleValue=Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    doubleValue=doubleValue;
                }
            }
        }
        return defaultVlue;
    }

    /*
        转为long
     */
    public static long castLong(Object object){
        return castLong(object,0);
    }

    public static long castLong(Object object,long defaultLong){
        long longValue=defaultLong;
        if(object!=null){
            String strVal=castString(object);
            if(StringUtil.isNotEmpty(strVal)){
                try {
                    longValue=Long.parseLong(strVal);
                }catch (NumberFormatException e){
                    longValue=defaultLong;
                }
            }
        }
        return longValue;
    }

    /*
        转为int
     */
    public static int castInt(Object object){
        return castInt(object,0);
    }

    public static int castInt(Object object,int defaultInt){
        int intValue=defaultInt;
        if(object!=null){
            String strVal=castString(object);
            if(StringUtil.isNotEmpty(strVal)){
                try {
                    intValue=Integer.parseInt(strVal);
                }catch (NumberFormatException e){
                    intValue=defaultInt;
                }
            }
        }
        return intValue;
    }
    /*
        转为boolean
     */
    public static boolean castBoolean(Object object){
        return castBoolean(object,false);
    }

    public static boolean castBoolean(Object object,boolean defaultBoolean){
        boolean booleanValue=defaultBoolean;
        if(object!=null){
            booleanValue=Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }
}
