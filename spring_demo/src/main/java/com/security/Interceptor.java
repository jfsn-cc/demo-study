package com.security;


import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {
    boolean before();

    /**
     * 取代原有事件方法
     ** @param invocation -- 回调参数，可以通过它的proceed方法，回调原有事件
     ** @return 原有事件返回对象
     ** @throws InvocationTargetException
     ** @throws IllegalAccessException
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    void after();

    void afterReturning();

    void afterThrowing();

    /**
     * 是否用around方法取代原有方法
     * @return
     */
    boolean useAround();

}