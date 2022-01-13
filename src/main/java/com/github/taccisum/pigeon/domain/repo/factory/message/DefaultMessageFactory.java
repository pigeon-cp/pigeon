package com.github.taccisum.pigeon.domain.repo.factory.message;

import com.github.taccisum.pigeon.core.entity.core.Message;
import com.github.taccisum.pigeon.core.repo.factory.MessageFactory;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/1/12
 */
@Component
public class DefaultMessageFactory implements MessageFactory {
    @Override
    public Message create(Long id) {
        // TODO:: provide default message
        throw new NotImplementedException();
    }

    @Override
    public boolean match(Long id, Args o) {
        return true;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
