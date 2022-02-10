package com.github.taccisum.pigeon.config;


import com.github.taccisum.domain.core.DomainException;
import com.github.taccisum.domain.core.exception.ErrorCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author liaojinfeng - jinfeng.liao@happy-seed.com
 * @since 0.1
 **/
@Slf4j
@ControllerAdvice
public class ErrorConfiguration {
    @ExceptionHandler(value = DomainException.class)
    public void handlerException(DomainException e) {
        log.warn("发生业务异常", e);
        // rethrow 交由统一处理，此处仅打印日志
        throw e;
    }

    /**
     * @since 0.2
     */
    @Bean
    public ErrorAttributes errorAttributes(ErrorCodeService errorCodeService) {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> attrs = super.getErrorAttributes(webRequest, options);
                Throwable error = this.getError(webRequest);
                if (error instanceof DomainException) {
                    attrs.put("code", errorCodeService.getErrorCode((DomainException) error));
                }
                return attrs;
            }
        };
    }
}
