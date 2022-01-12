package com.github.taccisum.pigeon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.taccisum.domain.core.EventBus;
import com.github.taccisum.domain.core.adapter.GuavaEventBusAdapter;
import com.github.taccisum.pigeon.domain.event.handler.DomainEventSubscriber;
import com.github.taccisum.pigeon.utils.JsonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Configuration
public class ApplicationAutoConfiguration implements InitializingBean {
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public EventBus eventBus(List<DomainEventSubscriber> subscribers) {
        com.google.common.eventbus.EventBus delegate = new com.google.common.eventbus.EventBus();
        for (DomainEventSubscriber subscriber : subscribers) {
            delegate.register(subscriber);
        }
        return new GuavaEventBusAdapter(delegate);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JsonUtils.setObjectMapper(objectMapper);
    }
}
