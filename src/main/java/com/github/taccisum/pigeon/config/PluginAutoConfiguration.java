package com.github.taccisum.pigeon.config;

import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.nio.file.Paths;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/1/14
 */
@Configuration
public class PluginAutoConfiguration {
    @Autowired
    private ApplicationContext context;

    @Bean
    public PluginManagerBean pluginManager() {
        PluginManagerBean pluginManagerBean = new PluginManagerBean();
        return pluginManagerBean;
    }

    public static class PluginManagerBean extends SpringPluginManager {
        public PluginManagerBean() {
            super(Paths.get("/tmp/jars"));
        }

        @PreDestroy
        public void destroy() {
            this.stopPlugins();
            this.unloadPlugins();
        }
    }
}
