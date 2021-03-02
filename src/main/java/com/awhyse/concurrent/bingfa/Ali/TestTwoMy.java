package com.awhyse.concurrent.bingfa.Ali;

/**
 * 三个线程，顺序循环打印
 * Created by whyse
 * on 2017/3/10 22:30
 */
public class TestTwoMy {

    static volatile  int  order = 1;
    static volatile  int  index = 0;

    public static void main(String[] args) {
        int count = 100;
        long time = System.currentTimeMillis();
        getStringAli1(count);
        System.err.println(System.currentTimeMillis()-time);

    }

    /**
     * 题目2：有3个线程和1个公共的字符数组。线程1的功能就是向数组输出A，线程2的
     * 功能就是向字符输出l，线程3的功能就是向数组输出i。要求按顺序向数组赋值AliAliAli
     * ，Ali的个数由线程函数1的参数指定。
     * @param count
     * @return
     */
    private static void getStringAli1(int count) {
        char[] charAli = new char[3*count];


        Runnable runnablei = new Runnable() {
            @Override
            public void run() {
                int c = count;
                while (c>0) {
                    if(order == 3) {
                        charAli[index++] = 'i';
                        order = 1;
                        c--;
                    }
//                    else{
//                        //主动释放cpu占有资源，使runable线程参与竞争;  似乎加不加时间一样
//                        Thread.yield();
//                    }
                }
            }
        };
        Thread iThd = new Thread(runnablei);

        Runnable runnablel = new Runnable() {
            @Override
            public void run() {
                int c = count;
                while (c>0) {
                    if(order == 2) {
                        charAli[index++] = 'l';
                        order = 3;
                        c--;
                    }
//                    else{
//                        //主动释放cpu占有资源，使runable线程参与竞争
//                        Thread.yield();
//                    }
                }
            }
        };
        Thread lThd = new Thread(runnablel);


        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                int c = count;
                while (c>0) {
                    if(order == 1) {
                        charAli[index++] = 'A';
                        order = 2;
                        c--;
                    }
//                    else{
//                        //主动释放cpu占有资源，使runable线程参与竞争
//                        Thread.yield();
//                    }
                }
            }
        };
        Thread AThd = new Thread(runnableA);


        try {
            AThd.start();
            lThd.start();
            iThd.start();
            iThd.join();

            System.err.println(charAli);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
