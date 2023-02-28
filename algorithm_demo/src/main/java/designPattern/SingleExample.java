package designPattern;


import java.util.concurrent.TimeUnit;

public class SingleExample {
    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                System.out.println(this.getName() + " 111");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(this.getName()+" 222");
            }
        }.start();
/*        System.out.println(LazySingleton.getLazySingleton(1));
        System.out.println(LazySingleton.getLazySingleton(2));*/
    }
}

//饿汉式， 一开始就已经创建好实例
 class Hungry {
    private String name;
    private final static Hungry hungry = new Hungry();
    private Hungry(){
    }

    public Hungry getHungry() {
        return hungry;
    }
}

//懒汉式
class LazySingleton {
    private static volatile LazySingleton lazySingleton = null;//防止指令重排和从主存中获取数据
    private LazySingleton() {}

    public static LazySingleton getLazySingleton(int n) throws InterruptedException {
        if (lazySingleton == null) {
            synchronized(LazySingleton.class) {
                TimeUnit.SECONDS.sleep(n);
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}