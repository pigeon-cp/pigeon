package com.github.taccisum.pigeon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022-01-12 02:52:58 PM Wed
 */
@Slf4j
@Component
public class SwaggerEndpointPrinter {
    @Autowired
    private ServerProperties serverProperties;

    @EventListener(classes = ApplicationStartedEvent.class)
    public void run(ApplicationStartedEvent e) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append("http://127.0.0.1:");
        url.append(Optional.ofNullable(serverProperties.getPort()).orElse(8080));
        String contextPath = serverProperties.getServlet().getContextPath();
        if (contextPath != null) {
            url.append("/").append(contextPath);
        }
        url.append("/swagger-ui.html");
        log.info("Open {}", url.toString());
    }
}
