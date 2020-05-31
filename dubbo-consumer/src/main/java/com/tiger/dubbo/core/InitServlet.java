package com.tiger.dubbo.core;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/index" ,loadOnStartup = 0)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("***************开始执行初始化任务调度****************");
        InvokeThreadPool.initInvoke();
        System.out.println("***************开始执行定时任务调度****************");
        InvokeThreadPool.scheduleInvoke();
    }
}
