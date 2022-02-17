package com.github.taccisum.pigeon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
@Data
@Component
@ConfigurationProperties(prefix = "pigeon")
public class ApplicationProperties {
    /**
     * 项目版本号
     */
    private String version;
    /**
     * 扩展插件相关配置
     */
    private Plugins plugins = new Plugins();

    @Data
    public static class Plugins {
        /**
         * 插件所在的根路径
         */
        private String root = "/usr/local/pigeon/plugins";
        /**
         * 插件所在路径
         */
        private List<String> paths = new ArrayList<>();
    }
}
