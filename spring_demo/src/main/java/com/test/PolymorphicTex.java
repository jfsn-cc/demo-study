package com.test;

/**
 * @创建人 ly
 * @时间 0217
 * @描述
 */
public class PolymorphicTex {
    static {
        int a = 10;
        System.out.println("加载输出" + a);
    }
    public static void main(String[] args) throws InterruptedException {
        Father fa = new Child();
        Thread.sleep(5000);
        fa.say();
    }
}


class Father {
    public void say() {
        System.out.println("this is father");
    }
}

class Child extends Father{
    public void say() {
        System.out.println("this is child");
    }
}