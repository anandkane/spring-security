package org.opus.explore.spring.springsecurityoauthcustomerservice.service;

import org.slf4j.*;
import org.springframework.stereotype.*;

@Component
public class LoggingBean {
    private static final Logger logger = LoggerFactory.getLogger(LoggingBean.class);

    public Object logAndReturn(Object object) {
        logger.info("Value: {}", object);
        return object;
    }
}