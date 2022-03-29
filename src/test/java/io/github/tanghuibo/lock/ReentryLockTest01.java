package io.github.tanghuibo.lock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tanghuibo
 * @date 2022/3/28下午6:11
 */
public class ReentryLockTest01 {

    Logger log = LoggerFactory.getLogger(ReentryLockTest01.class);

    @Test
    public void test() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock(true);

        ExecutorService executorService = Executors.newFixedThreadPool(20);


        for (int i = 1; i < 20; i++) {
            executorService.execute(() -> {
                try {
                    lock.lock();
                    log.info("{} lock", Thread.currentThread().getName());
                    Thread.sleep(100L);
                    log.info("{} unlock", Thread.currentThread().getName());
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(30L);

        executorService.execute(() -> {
            while (!lock.tryLock()) {

            }
            log.info("tryLock get Lock success");
            lock.unlock();
        });

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);



    }
}
