package com.company.project.test;

import com.company.project.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 使用Spring的@Async注解实现方法异步执行
 * Created by qince on 2015/3/18.
 */

public class SpringAsyncTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-service.xml");
        AsyncService asyncService = applicationContext.getBean(AsyncService.class);
        SpringAsyncTest springAsyncTest = new SpringAsyncTest();
        springAsyncTest.testAsync(asyncService);
        System.out.println("=========================================");
        springAsyncTest.testAsync2(asyncService);
        System.out.println("=========================================");

        // 注意如果想要异步功能的化，要单独新建一个spring管理的类，不要与其他的Service层的类混用
        try {
            System.out.println("testAsync3 start.");
            springAsyncTest.testAsync3();
            System.out.println("testAsync3 end.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testAsync(AsyncService asyncService) {
        System.out.println("使用Spring的@Async注解实现方法异步执行");

        try {
            asyncService.business();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("操作已完成！");
    }

    public void testAsync2(AsyncService asyncService) {

        System.out.println("使用Spring的@Async注解实现方法异步执行2");

        try {
           Future<Double> future = asyncService.business2();
           Double result = future.get();
            System.out.println("result:" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("操作已完成！2");
    }

    @Async
    public void testAsync3() throws InterruptedException {
        System.out.println("begin..........");
        Thread.sleep(2000);
        System.out.println("end............");
    }
}
