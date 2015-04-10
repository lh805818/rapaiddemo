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
public class AppleProxyStatic {
    IAppleService ias;

    public AppleProxyStatic(IAppleService ias) {
        this.ias = ias;
    }

    public Apple createIPhone(String name,float price) {
        return this.ias.createPhone(name,price);
    }
}
