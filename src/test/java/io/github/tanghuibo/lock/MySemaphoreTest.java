package io.github.tanghuibo.lock;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghuibo
 * @date 2022/4/1下午8:25
 */
public class MySemaphoreTest extends TestCase {

    Logger log = LoggerFactory.getLogger(MySemaphoreTest.class);

    @Test
    public void test() throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore(3);

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                try {
                    mySemaphore.acquire();
                    log.info("{} start", Thread.currentThread().getName());
                    Thread.sleep(500L);
                    log.info("{} end", Thread.currentThread().getName());
                    mySemaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }

}