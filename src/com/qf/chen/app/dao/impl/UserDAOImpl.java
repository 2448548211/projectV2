package com.qf.chen.app.dao.impl;

import com.qf.chen.app.dao.IUserDAO;
import com.qf.chen.app.entity.User;
import com.qf.chen.app.utils.DAOUtil;

import java.util.List;
/**
 * @author ChenWang
 * @className UserDAOImpl
 * @date 2020/10/13 19:16
 * @since JDK 1.8
 */
public class UserDAOImpl implements IUserDAO {
    private Object t;
    @Override
    public int insert(User user) {
        //插入语句
        String insertSql = "insert into users(username,password) values (?,?)";
        //调用工具类的数据库操作的一般方法
        return DAOUtil.excuteUpdate(insertSql,user.getUsername(),user.getPassword());
    }
    @Override
    public List<User> selectUserByName(User user) {
        //查询语句
        String selectSql = "select * from users where is_delete = 1 and username = ?";
        //调用工具类的数据库查询的一般方法
        return DAOUtil.excuteQuery(selectSql,User.class,user.getUsername());
    }


}
