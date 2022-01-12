package com.github.taccisum.pigeon.domain.exception;

/**
 * 数据不存在异常
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
public class DataNotFoundException extends DomainException {
    public DataNotFoundException(String key, Object id) {
        super("%s[%s]不存在", key, id);
    }
}
