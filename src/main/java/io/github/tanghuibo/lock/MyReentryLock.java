package io.github.tanghuibo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author tanghuibo
 * @date 2022/3/29下午2:37
 */
public class MyReentryLock implements Lock {

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int acquireCount) {
            int state = this.getState();
            Thread exclusiveOwnerThread = this.getExclusiveOwnerThread();
            Thread currentThread = Thread.currentThread();
            if(exclusiveOwnerThread == currentThread) {
                int newStatus = state + acquireCount;
                if(newStatus < 0) {
                    throw new Error("Maximum lock count exceeded");
                }
                setState(newStatus);
                return true;
            }
            if(state != 0) {
                return false;
            }
            setState(acquireCount);
            setExclusiveOwnerThread(currentThread);
            return true;
        }

        @Override
        protected boolean tryRelease(int releaseCount) {
            Thread exclusiveOwnerThread = getExclusiveOwnerThread();
            Thread currentThread = Thread.currentThread();
            if(exclusiveOwnerThread != currentThread) {
                throw new IllegalMonitorStateException();
            }
            int state = getState();
            int newStatus = state - releaseCount;
            if(newStatus < 0) {
                throw new IllegalMonitorStateException();
            }

            setState(newStatus);

            if(newStatus != 0) {
                return false;
            }
            setExclusiveOwnerThread(null);
            return true;
        }

        public Condition newCondition() {
            return new AbstractQueuedSynchronizer.ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
