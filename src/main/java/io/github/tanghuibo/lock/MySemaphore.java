package io.github.tanghuibo.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author tanghuibo
 * @date 2022/4/1下午8:19
 */
public class MySemaphore {

    final Sync sync;
    public MySemaphore(int status) {
        sync = new Sync(status);
    }

    public void acquire() {
        sync.acquireShared(1);
    }

    public void release() {
        sync.releaseShared(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {
        Sync(int status) {
            setState(status);
        }

        @Override
        protected int tryAcquireShared(int acquireCount) {
            while (true) {
                int state = getState();
                int afterStatus = state - acquireCount;
                if(afterStatus >= 0 && compareAndSetState(state, afterStatus)) {
                    return 1;
                }
                return -1;
            }
        }

        @Override
        protected boolean tryReleaseShared(int releaseCount) {
            while (true) {
                int state = getState();
                int afterStatus = state + releaseCount;
                if(afterStatus < 0) {
                    throw new Error("Maximum permit count exceeded");
                }
                if(compareAndSetState(state, afterStatus)) {
                    return true;
                }

            }
        }
    }
}
