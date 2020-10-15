package com.qf.chen.app.controller;

import com.qf.chen.app.entity.BaseResponseEntity;
import com.qf.chen.app.entity.User;
import com.qf.chen.app.service.IUserService;
import com.qf.chen.app.service.impl.UserServiceImpl;
import com.qf.chen.app.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenWang
 * @className RegisterController
 * @date 2020/10/13 19:14
 * @since JDK 1.8
 */
public class RegisterController extends HttpServlet {
    //这一行代码以后会优化，会由框架工具自动实现
    private IUserService userService = new UserServiceImpl();
    private Object User;

    //post 适用于 sql insert
    //put 适用于 sql update
    //get 使用于 sql select
    //delete 适用于 sql delete
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从请求传来的JSON对象中获取数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //注册服务
        boolean isSuccess = userService.register(new User(username,password));
        //初始化数据对象
        BaseResponseEntity<Boolean> pakage =null;
        if(isSuccess){
            pakage = BaseResponseEntity.success(isSuccess);
        }else{
            pakage = BaseResponseEntity.error();
        }
        ResponseUtil.sendJSON(resp,pakage);
    }
}
