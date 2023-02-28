package com.security;

import io.swagger.models.auth.In;
import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBean implements InvocationHandler {

    private Object target = null;
    private Interceptor interceptor = null;

    /**
     * 绑定代理对象
     * @param target 目标对象
     * @param interceptor 拦截器
     * @return 返回实例
     */
    public Object getProxyBean(Object target, Interceptor interceptor) {
        //建立对象
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = target;
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
        proxyBean.interceptor = interceptor;
        return proxy;
    }
    /**
     *
     * @param proxy 代理对象
     * @param method 代理方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Invocation invocation = new Invocation(target, method, args);
        Object reObject = null;
        try {
            if (this.interceptor.before()) {
                reObject = this.interceptor.around(invocation);
            } else {
                reObject = method.invoke(proxy, args);
            }
        } catch (Exception e) {
            this.interceptor.afterThrowing();
        }
        this.interceptor.after();
        this.interceptor.afterReturning();
        return reObject;
        // 异常标识
/*        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target, method, args);
        Object retObj = null;
        try {
            if (this.interceptor.before()) {
                retObj = this.interceptor.around(invocation);
            } else {
                retObj = method.invoke(target, args);
            }
        } catch (Exception ex) {
            // 产生异常
            exceptionFlag = true;
        }
        this.interceptor.after();
        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;*/
    }
}
