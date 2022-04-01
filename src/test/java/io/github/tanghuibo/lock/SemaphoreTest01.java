package io.github.tanghuibo.lock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghuibo
 * @date 2022/4/1下午8:02
 */
public class SemaphoreTest01 {

    Logger log = LoggerFactory.getLogger(SemaphoreTest01.class);

    @Test
    public void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 19; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    log.info("{} start", Thread.currentThread().getName());
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.submit(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    semaphore.release();
                    log.info("{} release data ", Thread.currentThread().getName());
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }
}
