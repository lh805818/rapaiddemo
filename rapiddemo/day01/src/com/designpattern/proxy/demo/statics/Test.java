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
public class Test {
    public static void main(String[] args) {
        IAppleService ias = new AppleService();
        Apple apple = ias.createPhone("IPhone 5s", 5288);

        AppleProxyStatic ap = new AppleProxyStatic(ias);
        Apple apple1 = ap.createIPhone("IPhone 6", 5288);

        AppleProxyDynamic apd = new AppleProxyDynamic();
        IAppleService iAppleService = (IAppleService)apd.bind(new AppleService());
        iAppleService.createPhone("IPhone 6 Plus",6288);
    }
}
