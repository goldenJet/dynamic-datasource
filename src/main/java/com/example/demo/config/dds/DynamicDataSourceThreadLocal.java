package com.example.demo.config.dds;

/**
 * 使用ThreadLocal 保存数据源信息
 * Created by Jet.chen on 2017/9/4.
 */
public class DynamicDataSourceThreadLocal {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void setDataSource(String name){
        THREAD_LOCAL.set(name);
    }

    public static String getDataSource(){
        return THREAD_LOCAL.get();
    }

    public static void removeDataSource(){
        THREAD_LOCAL.remove();
    }
}
