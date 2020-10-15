package com.qf.chen.app.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ChenWang
 * @className DBUtil
 * @date 2020/10/13 19:18
 * @since JDK 1.8
 */
public class DBUtil {
    /**
     * 导入对应的驱动 --类路径
     */
    private static String CLASSNAME = null;
    /**
     * url地址
     */
    private static String URL = null;
    /**
     * 登录数据库的用户名
     */
    private static String USERNAME = null;
    /**
     * 登录数据库的密码
     */
    private static String PASSWORD = null;
    static{
        try {
            //给classname,URL,username,password进行赋值
            Properties properties = new Properties();
            /* Class.getClassLoader.getResourceAsStream(String path):默认则是从ClassPath根下获取，
             * path不能以'/'开头，最终是由ClassLoader获取资源。
             * 那ServletContext?? 项目搭载有条件?
             */
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            CLASSNAME = properties.getProperty("classname");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接 -- 参数值在jdbc.properties里已经配置好了
     * @return java.sql.Connection  返回数据库连接
     * @author ChenWang
     * @date 2020/10/13 21:12
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //1.加载驱动
            Class.forName(CLASSNAME);
            //2.DriverManager获取数据库连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 释放资源
     * @param closeables    可变参量，类型为AutoCloseable接口的实现类
     * @author ChenWang
     * @date 2020/10/13 21:12
     */
    public static void closeAll(AutoCloseable...closeables){
        for (AutoCloseable closeable:closeables){
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
