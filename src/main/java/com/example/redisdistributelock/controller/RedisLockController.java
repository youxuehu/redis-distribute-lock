package com.example.redisdistributelock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RestController
@RequestMapping(value = "/redis")
public class RedisLockController {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisLockRegistry redisLockRegistry;
    @GetMapping("/lock")
    public void redisLock() {
        Lock lock = null;
        try {
            lock = redisLockRegistry.obtain("lock");
            boolean tryLock = lock.tryLock(3, TimeUnit.SECONDS);
            LOG.info("tryLock is :" + tryLock);
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            LOG.info("手慢了，已经没有了哦～" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
