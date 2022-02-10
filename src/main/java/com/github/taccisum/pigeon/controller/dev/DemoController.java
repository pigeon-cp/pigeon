package com.github.taccisum.pigeon.controller.dev;

import com.github.taccisum.domain.core.DomainException;
import com.github.taccisum.domain.core.exception.annotation.ErrorCode;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Profile({"dev", "local"})
@RestController
@RequestMapping("demos")
public class DemoController {

    @GetMapping("fails")
    public void fail() {
        throw new DomainException("业务异常");
    }

    @GetMapping("fails/foo")
    public void fooFail() {
        throw new FooException();
    }

    @GetMapping("errors")
    public void error() {
        throw new RuntimeException("系统异常");
    }

    @ErrorCode("FOO")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static class FooException extends DomainException {
        public FooException() {
            super("");
        }
    }
}
