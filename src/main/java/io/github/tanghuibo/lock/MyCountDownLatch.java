package io.github.tanghuibo.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author tanghuibo
 * @date 2022/3/29下午5:40
 */
public class MyCountDownLatch {

    private final Sync sync;

    public MyCountDownLatch(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count < 0");
        }
        this.sync = new Sync(count);
    }

    static class Sync extends AbstractQueuedSynchronizer {

        Sync(int status) {
            setState(status);
        }

        @Override
        protected boolean tryReleaseShared(int releaseCount) {
            while (true) {
                int state = getState();
                int newStatus = state - releaseCount;
                if(compareAndSetState(state, newStatus)) {
                    return newStatus == 0;
                }
            }
        }

        @Override
        protected int tryAcquireShared(int acquireCount) {
            return getState() == 0 ? 1 : -1;
        }
    }

    public void countDown() {
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
}
