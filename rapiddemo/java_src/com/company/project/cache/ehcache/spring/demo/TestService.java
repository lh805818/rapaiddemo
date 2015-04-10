package com.company.project.cache.ehcache.spring.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qince on 2015/3/19.
 */
@Service
public class TestService {
    private static Map<Long,User> userMap = new HashMap<Long, User>();
    private final static Log log = LogFactory.getLog(TestService.class);

    @PostConstruct
    public void initUserMap() {
        userMap.put(10000L,new User(10000L,"admin"));
        userMap.put(10001L,new User(10001L,"test"));
        userMap.put(10002L,new User(10002L,"system"));
    }

    public User getUser(Long id) {
        log.info("start to find user by " + id);

        if (id == null) return null;

        User user = userMap.get(id);
        log.info(user);

        return user;
    }
}
