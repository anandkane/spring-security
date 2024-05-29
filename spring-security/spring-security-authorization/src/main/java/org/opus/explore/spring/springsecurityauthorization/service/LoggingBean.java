package org.opus.explore.spring.springsecurityauthorization.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingBean {
    private static final Logger logger = LoggerFactory.getLogger(LoggingBean.class);

    public Object logAndReturn(Object object) {
        logger.info("Value: {}", object);
        return object;
    }
}