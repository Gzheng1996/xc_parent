package com.xuecheng.manage_cms.service;

import java.util.concurrent.*;

public class HoldSeatClass {

    //抢座人数
    public static int peopleNum=1000;
    //座位数
    public volatile static int seat=10;


    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
       final CountDownLatch countDownLatch = new CountDownLatch(seat);

        for (int i = 0; i < peopleNum; i++) {
            if(seat>0){
                executorService.execute(()->{
                   synchronized (HoldSeatClass.class){
                       if(seat>0){//双重检查
                           --seat;
                           System.out.println(Thread.currentThread().getName()+"恭喜抢座成功");
                           countDownLatch.countDown();
                       }
                   }
                });
            }
        }

        try {
            executorService.shutdown();
            countDownLatch.await();
            System.out.println("好了，座位已经被占满了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




    }
