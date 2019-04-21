package com.owen;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LoggerTest {

    @Test
    public void testLog(){
        log.info("Hello");
    }

}
