package com.qf.chen.app.dao;

import com.qf.chen.app.entity.User;

import java.util.List;

/**
 * @author ChenWang
 * @className IUserDAO
 * @date 2020/10/13 19:15
 * @since JDK 1.8
 */
public interface IUserDAO {
    int insert(User user);

    List<User> selectUserByName(User user);
}
