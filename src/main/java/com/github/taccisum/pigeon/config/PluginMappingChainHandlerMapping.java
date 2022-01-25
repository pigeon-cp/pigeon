package com.github.taccisum.pigeon.config;

import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 此 {@link HandlerMapping} 将链式委托插件中的 {@link HandlerMapping} 进行处理，这样即使是在插件中定义的 Controller 也可以被检测到，使得插件具备自由扩展 API 的能力
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.2
 */
@Component
public class PluginMappingChainHandlerMapping implements HandlerMapping, Ordered {
    @Resource
    private SpringPluginManager pluginManager;

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        for (PluginWrapper plugin : pluginManager.getPlugins()) {
            ApplicationContext context = ((SpringPlugin) plugin.getPlugin()).getApplicationContext();
            Map<String, HandlerMapping> mappings = context.getBeansOfType(HandlerMapping.class);
            for (HandlerMapping mapping : mappings.values()) {
                if (mapping instanceof RequestMappingHandlerMapping) {
                    HandlerExecutionChain handler = mapping.getHandler(request);
                    if (handler != null) {
                        return handler;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
