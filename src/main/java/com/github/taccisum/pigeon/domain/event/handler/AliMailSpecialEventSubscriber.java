package com.github.taccisum.pigeon.domain.event.handler;

import com.github.taccisum.pigeon.domain.entity.core.Message;
import org.springframework.stereotype.Component;

/**
 * 针对阿里云邮件的特殊逻辑处理，避免污染主业务逻辑
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class AliMailSpecialEventSubscriber implements DomainEventSubscriber {
    @Override
    public void listen(Message.DeliverEvent e) throws Throwable {
        // TODO:: 未开启邮件跟踪的话只能特别处理，假定邮件投递成功即为发送成功
        e.getPublisher().publish(new Message.SentEvent(e.getSuccess()));
    }
}
