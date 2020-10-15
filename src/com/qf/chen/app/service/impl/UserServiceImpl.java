package com.qf.chen.app.service.impl;

import com.qf.chen.app.dao.IUserDAO;
import com.qf.chen.app.dao.impl.UserDAOImpl;
import com.qf.chen.app.entity.User;
import com.qf.chen.app.service.IUserService;

import java.util.List;

/**
 * @author ChenWang
 * @className UserServiceImpl
 * @date 2020/10/13 19:15
 * @since JDK 1.8
 */
public class UserServiceImpl implements IUserService {
    //后续框架会优化这一段代码
    private IUserDAO userDAO = new UserDAOImpl();

    /**
     * 注册用户
     * @param user  用于注册的指定用户，一般带有用户名和密码
     * @return boolean  true表示注册成功，false表示注册失败
     * @author ChenWang
     * @date 2020/10/13 20:13
     */
    @Override
    public boolean register(User user) {
        boolean flag = false;
        //只需要进行一次查询操作
        List<User> list = userDAO.selectUserByName(user);
        if(list==null||list.size()==0){
            try{
                flag = userDAO.insert(user)>0;
            }catch(Exception e){
                System.out.println("插入数据失败");
            }
        }else{
            System.out.println("用户名已存在");
        }
        return flag;
    }
}
