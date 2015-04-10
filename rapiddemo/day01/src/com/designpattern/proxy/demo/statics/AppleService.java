/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.designpattern.proxy.demo.statics;

/**
 * Created by qince on 2014/12/22.
 */
public class AppleService implements IAppleService {
    @Override
    public Apple createPhone(String name, float price) {
        Apple apple = new Apple(name,price);
        System.out.println("创建了一部IPhone：\n" + apple);
        return apple;
    }
}
