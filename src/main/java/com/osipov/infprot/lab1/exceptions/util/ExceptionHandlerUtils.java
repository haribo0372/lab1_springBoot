package com.osipov.infprot.lab1.exceptions.util;

import com.osipov.infprot.lab1.exceptions.model.ApiError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandlerUtils {

    public static void loggingInfo(String preMessage, ApiError apiError) {
        log.info("{} -> {}", preMessage, apiError);
    }

    public static void loggingErr(String preMessage, ApiError apiError) {
        log.error("{} -> {}", preMessage, apiError);
    }

}