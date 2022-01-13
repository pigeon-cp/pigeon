package com.github.taccisum.pigeon.domain.repo.factory.message;

import com.github.taccisum.pigeon.core.entity.core.Message;
import com.github.taccisum.pigeon.domain.entity.message.AliCloudMailMessage;
import com.github.taccisum.pigeon.core.repo.factory.MessageFactory;
import org.springframework.stereotype.Component;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/1/12
 */
@Component
public class AliCloudMailMessageFactory implements MessageFactory {
    @Override
    public Message create(Long id) {
        return new AliCloudMailMessage(id);
    }

    @Override
    public boolean match(Long id, Args o) {
        return o.getType().equals("MAIL") && o.getSpType().equals("ALI_CLOUD");
    }
}
