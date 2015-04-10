package com.company.project.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by qince on 2015/3/18.
 */
@Service
public class AsyncService {
    @Async
    public void business() throws InterruptedException {
        System.out.println("业务方法开始执行");
        // 模拟业务操作耗时较长的过程，线程休眠3秒
        Thread.sleep(3000);
        System.out.println("业务方法执行结束");
    }

    /**
     * 得到异步执行结果
     * @return
     * @throws InterruptedException
     */
    @Async
    public Future<Double> business2() throws InterruptedException {
        System.out.println("业务方法开始执行");
        // 模拟业务操作耗时较长的过程，线程休眠3秒
        Thread.sleep(3000);
        System.out.println("业务方法执行结束");
        return new AsyncResult<Double>(100d);
    }
}
