package com.github.taccisum.pigeon.domain.event.handler;

import com.github.taccisum.pigeon.domain.entity.core.Message;
import org.springframework.stereotype.Component;

/**
 * 更新消息状态的事件订阅器
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Component
public class UpdateMessageStatusEventSubscriber implements DomainEventSubscriber {
    @Override
    public void listen(Message.SentEvent e) throws Throwable {
        e.getPublisher().markSent(e.getSuccess());
    }
}
