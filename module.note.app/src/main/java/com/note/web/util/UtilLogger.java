package com.note.web.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilLogger {

    public static void info(String message) {
        log.info(message);
    }

    public static void error(String message, Object e) {
        log.error(message, e);
    }

}
