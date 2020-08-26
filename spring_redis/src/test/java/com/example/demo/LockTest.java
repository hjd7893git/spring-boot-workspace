package com.example.demo;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjd on 2020-08-25 17:26
 * --> 锁
 * <p>
 * （1）使用lock()
 * <p>
 * 和使用Synchronized关键字是一样的效果，直接去获取锁。成功了就ok了，失败了就阻塞等待了。不同的是lock锁是可重入锁，所以还是有不一样的地方：
 * <p>
 * 当锁可用，并且当前线程没有持有该锁，直接获取锁并把count set为1.
 * 当锁可用，并且当前线程已经持有该锁，直接获取锁并把count增加1.
 * 当锁不可用，那么当前线程被阻塞，休眠一直到该锁可以获取，然后把持有count设置为1.
 *
 * （2）使用tryLock()
 * <p>
 * 当获取锁时，只有当该锁资源没有被其他线程持有才可以获取到，并且返回true，同时设置持有count为1；
 * 当获取锁时，当前线程已持有该锁，那么锁可用时，返回true，同时设置持有count加1；
 * 当获取锁时，如果其他线程持有该锁，无可用锁资源，直接返回false，这时候线程不用阻塞等待，可以先去做其他事情；
 * 即使该锁是公平锁fairLock，使用tryLock()的方式获取锁也会是非公平的方式，只要获取锁时该锁可用那么就会直接获取并返回true。这种直接插入的特性在一些特定场景是很有用的。但是如果就是想使用公平的方式的话，可以试一试tryLock(0, TimeUnit.SECONDS)，几乎跟公平锁没区别，只是会监测中断事件。
 **/
@Logger(name = "info")
public class LockTest {

    public RedissonClient getRedisson() {

        Config config = new Config();
        //config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        config.useSingleServer().setAddress("redis://" + "192.168.220.132" + ":" + "6379");
        config.setLockWatchdogTimeout(30*1000); //看门狗,对锁进行续期，当该业务系统down掉之后，看门狗就没了，锁就自动释放了，避免死锁。它的作用是在Redisson实例被关闭前，不断的延长锁的有效期
        //添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        return Redisson.create(config);
    }


    @Test
    public void testLock() throws Exception {
        RedissonClient redissonClient = getRedisson();
        RLock rLock = redissonClient.getLock("lbhTestLock");
//        rLock.lock(); //持续加锁，直到解锁（unloke）才释放  ----------【搭配看门口使用】

        // 加锁以后2秒钟自动解锁
        // 无需调用unlock方法手动解锁
        rLock.lock(3, TimeUnit.SECONDS);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (rLock.tryLock(4, 3, TimeUnit.SECONDS)) { //等待时间
                        System.out.println("成功获取锁");
                    } else {
                        System.out.println("未获取锁");
                    }
                    //可以做其它事情
                    Thread.sleep(3000);
                } catch (Exception e) {

                } finally {
                    System.out.println("锁的状态:" + rLock.isLocked());

                    if (rLock.isLocked())  //检查是否死锁
                        if (rLock.isHeldByCurrentThread()){ //检查是否是当线程的锁
                            System.out.println("执行解锁！！");
                            rLock.unlock();
                        }
                }

            }
        }).start();
        Thread.sleep(40000);
    }
}

