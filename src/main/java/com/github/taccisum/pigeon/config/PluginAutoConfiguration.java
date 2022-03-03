package com.github.taccisum.pigeon.config;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginRuntimeException;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
                properties.getPlugins().getRoot(),
                properties.getPlugins().getPaths()
        );
    }

    @Slf4j
    public static class PluginManagerBean extends SpringPluginManager {
        private List<String> plugins;

        public PluginManagerBean(String pluginRoot, List<String> plugins) {
            super(Paths.get(pluginRoot));
            this.plugins = plugins;
            // should version of pigeon-core instead pigeon
            // TODO:: move to pigeon-core next version
            this.setSystemVersion("0.2.0");
        }

        public PluginManagerBean(Path pluginsRoot) {
            super(pluginsRoot);
        }

        @Override
        public void loadPlugins() {
            for (String plugin : plugins) {
                try {
                    this.loadPlugin(Paths.get(plugin));
                } catch (PluginRuntimeException e) {
                    log.error(e.getMessage(), e);
                }
            }
            super.loadPlugins();
        }

        @PreDestroy
        public void destroy() {
            this.stopPlugins();
            this.unloadPlugins();
        }
    }
}
