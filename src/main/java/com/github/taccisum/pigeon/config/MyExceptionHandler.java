package com.github.taccisum.pigeon.config;


import com.github.taccisum.domain.core.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author liaojinfeng - jinfeng.liao@happy-seed.com
 * @since 0.1
 **/
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = DomainException.class)
    public void handlerException(DomainException e) {
        log.warn("发生业务异常", e);
        // rethrow 交由统一处理，此处仅打印日志
        throw e;
    }
}
