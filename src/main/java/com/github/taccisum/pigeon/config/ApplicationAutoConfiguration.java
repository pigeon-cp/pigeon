package com.github.taccisum.pigeon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.taccisum.domain.core.EventBus;
import com.github.taccisum.domain.core.adapter.GuavaEventBusAdapter;
import com.github.taccisum.pigeon.core.PigeonContext;
import com.github.taccisum.pigeon.core.event.handler.DomainEventSubscriber;
import com.github.taccisum.pigeon.core.repo.SubMassRepo;
import com.github.taccisum.pigeon.core.service.AsyncDeliverSubMassService;
import com.github.taccisum.pigeon.core.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Slf4j
@Configuration
public class ApplicationAutoConfiguration implements InitializingBean {
    @Autowired
    private ObjectMapper objectMapper;
    @Resource
    private ApplicationContext context;

    @Bean
    public EventBus eventBus(List<DomainEventSubscriber> subscribers) {
        com.google.common.eventbus.EventBus delegate = new com.google.common.eventbus.EventBus();
        for (DomainEventSubscriber subscriber : subscribers) {
            delegate.register(subscriber);
        }
        return new GuavaEventBusAdapter(delegate);
    }

    @Bean
    @Profile("local")
    public AsyncDeliverSubMassService syncDeliverSubMassService(SubMassRepo subMassRepo) {
        log.warn("本地模式下消息子集合分发将同步执行，若此消息出现在线上环境，请及时排查是否配置或代码错误");
        return new AsyncDeliverSubMassService.Default(subMassRepo) {
            @Override
            public void publish(DeliverSubMassCommand command) {
                handle(command);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncDeliverSubMassService asyncDeliverSubMassService(SubMassRepo subMassRepo) {
        return new AsyncDeliverSubMassService.Default(subMassRepo);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JsonUtils.setObjectMapper(objectMapper);
        PigeonContext.setMainContext(context);
    }
}
