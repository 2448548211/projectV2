package com.qf.chen.app.service;

import com.qf.chen.app.entity.User;

/**
 * @author ChenWang
 * @interfaceName IUserService
 * @date 2020/10/13 19:14
 * @since JDK 1.8
 */
public interface IUserService {
    /**
     * 注册用户
     * @param user  用于注册的指定用户，一般带有用户名和密码
     * @return boolean  true表示注册成功，false表示注册失败
     * @author ChenWang
     * @date 2020/10/13 20:07
     */
    boolean register(User user);
}
