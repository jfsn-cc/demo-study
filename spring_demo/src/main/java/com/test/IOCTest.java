package com.test;

import com.configration.AppConfig;
import com.security.MyInterceptor;
import com.security.ProxyBean;
import com.service.HelloService;
import com.service.imp.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Slf4j
public class IOCTest {
    public static void main(String[] args) throws NoSuchMethodException {
      //  ApplicationContext ctx  = new AnnotationConfigApplicationContext(AppConfig.class);
      //  HelloServiceIml helloServiceIml1 = ctx.getBean("HelloServiceIml", HelloServiceIml.class);
//        User user = ctx.getBean(User.class);
//        log.info(user.getId()+"");
        HelloService helloService = new HelloServiceImpl();
        ProxyBean proxyBean = new ProxyBean();
        HelloService proxy = (HelloService) proxyBean.getProxyBean(helloService, new MyInterceptor());

        proxy.sayHello("测试代理");
    }

}
