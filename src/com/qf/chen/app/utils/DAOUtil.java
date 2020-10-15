package com.qf.chen.app.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenWang
 * @className DAOUtil
 * @date 2020/10/14 19:06
 * @since JDK 1.8
 */
public class DAOUtil {
    //还可以使用Object类作为参数实现简单的通用型方法
    //下面使用泛型来实现通用型方法
    /**
     * 通过泛型来设定通用型的sql执行语句
     * @param sql       指定的sql语句
     * @param tClass    期望的集合所保存的类型
     * @param objs      sql语句需要的参数
     * @return java.util.List<T>    结果集合
     * @author ChenWang
     * @date 2020/10/14 19:04
     */
    public static <T> List<T> excuteQuery(String sql, Class<T> tClass, Object...objs){
        List<T> list = null;
        Connection conn = null;
        PreparedStatement ps = null;
        T t = null;
        try{
            list = new ArrayList<>();
            Field[] fields = tClass.getDeclaredFields();
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <objs.length ; i++) {
                ps.setObject(i+1,objs[i]);
            }
            ResultSet rSet = ps.executeQuery();
            while(rSet.next()){
                t = tClass.newInstance();
                for(Field field : fields){
                    field.setAccessible(true);
                    //关系型数据库数据类型限制,所以反射带String参数的valueOf方法
                    Method valueOf = field.getType().getDeclaredMethod("valueOf", String.class);
                    //取出的数据转换成String类型再往回转换成对象中对应的数据类型
                    //防止数据库中所存数据类型取出来之后和对象的数据类型不一致  eg.Character类型
                    field.set(t,valueOf.invoke(field.getType().newInstance(),rSet.getObject(field.getName())+""));
                    //如果数据库和对象之间有严格的类型映射，就直接set
                    //field.set(t,rSet.getObject(field.getName()));
                }
                list.add(t);
            }
        }catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeAll(ps,conn);
        }
        return list;
    }
    /**
     * 通用的操作数据库语句
     * @param sql   指定的操作语句
	 * @param objs  传入的参数
     * @return int  返回影响的行数
     * @author ChenWang
     * @date 2020/10/14 20:24
     */
    public static int excuteUpdate(String sql, Object...objs){
        int rSet = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <objs.length ; i++) {
                ps.setObject(i+1,objs[i]);
            }
            rSet = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeAll(ps,conn);
        }
        return rSet;
    }
}
