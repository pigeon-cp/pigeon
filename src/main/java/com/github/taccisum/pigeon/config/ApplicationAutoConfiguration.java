package com.github.taccisum.pigeon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.taccisum.domain.core.EventBus;
import com.github.taccisum.domain.core.adapter.GuavaEventBusAdapter;
import com.github.taccisum.domain.core.exception.ErrorCode;
import com.github.taccisum.domain.core.exception.ErrorCodeMapping;
import com.github.taccisum.domain.core.exception.ErrorCodeService;
import com.github.taccisum.swagger.configurer.DocketBuilder;
import com.github.taccisum.swagger.configurer.concrete.DefaultDescriptionBuilder;
import com.github.taccisum.swagger.configurer.config.SwaggerProperties;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import pigeon.core.PigeonContext;
import pigeon.core.docs.err.ErrorCodeFinder;
import pigeon.core.event.handler.DomainEventSubscriber;
import pigeon.core.repo.SubMassRepo;
import pigeon.core.service.AsyncDeliverSubMassService;
import pigeon.core.utils.JsonUtils;
import springfox.documentation.spring.web.plugins.Docket;

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


    @Bean
    public Docket docket(SwaggerProperties properties, Environment environment, ErrorCodeFinder errorCodeFinder, ErrorCodeService errorCodeService) {
        DocketBuilder builder = new DocketBuilder(properties);

        StringBuilder sb = new StringBuilder();
        for (ErrorCode code : errorCodeFinder.findAll()) {
            sb.append("<li>")
                    .append(code.value())
                    .append(": ")
                    .append(code.description())
                    .append("</li>");
        }
        builder.setDescriptionBuilder(new DefaultDescriptionBuilder(environment, properties.getInfo().getDescription()) {
            @Override
            public String build() {
                return super.build() + "</br>" +
                        "<p>请求返回 HTTP Status 400 代表发生了业务异常，此时可以通过响应头 code 获取到错误码，如下：</p>" +
                        "<ul>" +
                        sb.toString() +
                        "</ul>"
                        ;
            }
        });
        return builder.build();
    }

    @Bean
    public ErrorCodeFinder errorCodeFinder(ErrorCodeMapping errorCodeMapping) {
        return new ErrorCodeFinder(
                Lists.newArrayList(
                        "pigeon.core.excp",
                        "pigeon.core.entity.core",
                        "com.github.taccisum.pigeon.ext.aliyun.entity.sp"
                ),
                errorCodeMapping);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JsonUtils.setObjectMapper(objectMapper);
        PigeonContext.setMainContext(context);
    }
}
