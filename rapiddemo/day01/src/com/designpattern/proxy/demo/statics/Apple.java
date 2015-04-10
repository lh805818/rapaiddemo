package com.designpattern.proxy.demo.statics;

/**
 * Created by qince on 2014/12/22.
 */
public class Apple {
    private String name;
    private float price;

    public Apple() {
    }

    public Apple(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
