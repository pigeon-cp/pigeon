package com.github.taccisum.pigeon.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 业务异常
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Object... args) {
        super(String.format(message, args));
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }
}
