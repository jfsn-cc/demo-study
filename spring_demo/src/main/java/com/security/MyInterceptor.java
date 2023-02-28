package com.security;

import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;

public class MyInterceptor implements Interceptor {

    @Override
    public boolean before() {
        System.out.println("before ....");
        return true;
    }

    /**
     * 取代原有事件方法
     * * @param invocation -- 回调参数，可以通过它的proceed方法，回调原有事件
     * * @return 原有事件返回对象
     * * @throws InvocationTargetException
     * * @throws IllegalAccessException
     */
    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before ....");
        Object proceed = invocation.proceed();
        System.out.println("around after ....");
        return proceed;
    }

    @Override
    public void after() {
        System.out.println("after ...");
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning ...");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing ...");
    }

    /**
     * 是否用around方法取代原有方法
     *
     * @return
     */
    @Override
    public boolean useAround() {
        return true;
    }
}
