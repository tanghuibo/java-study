# JAVA 学习

## MyReentryLockTest

自己封装一个简易的可重入锁，主要实现两个方法: `tryAcquire` 和 `tryRelease`

## ReentryLocTest01

ReentryLock 分为公平锁和非公平锁，但是 tryLock 默认是不公平的

以下为输出日志

```text
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-1 lock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-1 unlock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-2 lock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-2 unlock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] tryLock get Lock success
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-4 lock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-4 unlock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-3 lock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-3 unlock
INFO  | 2022-03-28 18:34:47 [ck.ReentryLockTest01] pool-1-thread-6 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-6 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-5 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-5 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-7 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-7 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-8 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-8 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-9 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-9 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-10 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-10 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-11 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-11 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-12 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-12 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-13 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-13 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-14 lock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-14 unlock
INFO  | 2022-03-28 18:34:48 [ck.ReentryLockTest01] pool-1-thread-15 lock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-15 unlock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-16 lock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-16 unlock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-17 lock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-17 unlock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-18 lock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-18 unlock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-19 lock
INFO  | 2022-03-28 18:34:49 [ck.ReentryLockTest01] pool-1-thread-19 unlock
```