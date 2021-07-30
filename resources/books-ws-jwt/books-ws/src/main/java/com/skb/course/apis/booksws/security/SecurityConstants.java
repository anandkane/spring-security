package com.skb.course.apis.booksws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class SecurityConstants {

    public static final byte[] secret = System.getProperty("jwt.secret").getBytes();
}
