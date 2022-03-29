package io.github.tanghuibo.lock;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghuibo
 * @date 2022/3/29下午2:46
 */
public class MyReentryLockTest {

    Logger log = LoggerFactory.getLogger(MyReentryLockTest.class);

    @Test
    public void test() throws InterruptedException {
        MyReentryLock lock = new MyReentryLock();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 1; i < 20; i++) {
            executorService.execute(() -> {
                try {
                    lock.lock();
                    log.info("{} lock1", Thread.currentThread().getName());
                    lock.lock();
                    log.info("{} lock2", Thread.currentThread().getName());
                    Thread.sleep(100L);
                    log.info("{} unlock2", Thread.currentThread().getName());
                    lock.unlock();
                    log.info("{} unlock1", Thread.currentThread().getName());
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
    }

}