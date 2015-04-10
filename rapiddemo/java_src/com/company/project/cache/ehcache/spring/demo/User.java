package com.company.project.cache.ehcache.spring.demo;

import java.io.Serializable;

/**
 * Created by qince on 2015/3/19.
 */
public class User implements Serializable {
    private Long id;
    private String name;

    public User() {
    }

    public User(Long id, String name) {
            this.id = id;
            this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
