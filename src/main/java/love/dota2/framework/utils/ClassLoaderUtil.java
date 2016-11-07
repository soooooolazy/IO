package love.dota2.framework.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ClassLoaderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderUtil.class);

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载某个类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz;
        try {
            clazz=Class.forName(className,isInitialized,getClassLoader());
        }catch (ClassNotFoundException e){
            LOGGER.error("load class failed",e);
            throw new RuntimeException(e);
        }
        return clazz;
    }
    public static Class<?> loadClass(String className){
        return loadClass(className,true);
    }
    /**
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> set=new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls=getClassLoader().getResources(packageName.replace(".", "/"));
            while(urls.hasMoreElements()){
                URL url=urls.nextElement();
                if(url!=null){
                    String protocol=url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath=url.getPath().replace("%20"," ");
                        addClass(set,packagePath,packageName);
                    }else if(protocol.equals("jar")){
                        JarURLConnection jarURLConnection=(JarURLConnection)url.openConnection();
                        if(jarURLConnection!=null){
                            JarFile jarFile=jarURLConnection.getJarFile();
                            if(jarFile!=null){
                                Enumeration<JarEntry> jarEntries=jarFile.entries();
                                while (jarEntries.hasMoreElements()){
                                    JarEntry jarEntry=jarEntries.nextElement();
                                    String jarEntryName=jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className=jarEntryName.substring(0,jarEntryName.lastIndexOf(",")).replaceAll("/",".");
                                        doAddClass(set,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error("get class set failed",e);
            throw  new RuntimeException(e);
        }
        return  set;
    }

    private static void addClass(Set<Class<?>> set, String packagePath, String packageName) {
        File[] files=new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
            }
        });

        for(File file:files){
            String fileName=file.getName();
            if(file.isFile()){
                String className=fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtil.isNotEmpty(packageName)){
                    className=packageName+"."+className;
                }
                doAddClass(set,className);
            }else{
                String subPackagePath=fileName;
                if(StringUtil.isNotEmpty(packagePath)){
                    subPackagePath=packagePath+"/"+subPackagePath;
                }
                String subPackageName=fileName;
                if(StringUtil.isNotEmpty(subPackageName)){
                    subPackageName=packageName+"."+subPackageName;
                }
                addClass(set,subPackagePath,subPackageName);
            }
        }

    }

    private static void doAddClass(Set<Class<?>> set, String className) {
        Class<?> clazz=loadClass(className,false);
        set.add(clazz);
    }
}