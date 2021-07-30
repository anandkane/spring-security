package com.skb.course.apis.booksws.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private User user = null;

    public UserService() {

        user = new User("myusername", "$2y$12$4bavRcFDK8vjIUweLwetZuxnsBGcQqETtoX866/Ztli9Xjsc8PHbm",
                true);
    }

    public User getUserByUsername(String username) {

        if(user.getUsername().equals(username)) {
            return user;
        } else {
            return null;
        }
    }
}
