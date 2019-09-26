package com.awhyse.concurrent.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description
 * @author xumin
 * @date 2018/9/29 下午1:30
 */
public class LockSupportTest {
    public static void main(String[] args) {
        lockSupportTest1();
    }

    private static void lockSupportTest1() {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
    }
}
