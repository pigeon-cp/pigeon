package com.github.taccisum.pigeon.config;

import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/1/14
 */
@Configuration
public class PluginAutoConfiguration {
    @Autowired
    private ApplicationProperties properties;

    @Bean
    public PluginManagerBean pluginManager() {
        return new PluginManagerBean(
                Paths.get(properties.getPlugins().getPath())
        );
    }

    public static class PluginManagerBean extends SpringPluginManager {
        public PluginManagerBean() {
            super(Paths.get("/usr/local/pigeon/plugins"));
        }

        public PluginManagerBean(Path pluginsRoot) {
            super(pluginsRoot);
        }

        @PreDestroy
        public void destroy() {
            this.stopPlugins();
            this.unloadPlugins();
        }
    }
}
