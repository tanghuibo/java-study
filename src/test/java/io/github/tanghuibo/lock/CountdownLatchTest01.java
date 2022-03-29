package io.github.tanghuibo.lock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghuibo
 * @date 2022/3/29下午5:31
 */
public class CountdownLatchTest01 {
    Logger log = LoggerFactory.getLogger(MyReentryLockTest.class);
    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(11);


        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                countDownLatch.countDown();
                log.info("{} init", Thread.currentThread().getName());
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{} over", Thread.currentThread().getName());
            });
        }
        log.info("main init");
        Thread.sleep(1000L);
        log.info("main start");
        countDownLatch.countDown();

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
    }
}
