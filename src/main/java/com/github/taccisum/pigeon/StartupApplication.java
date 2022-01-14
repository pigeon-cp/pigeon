package com.github.taccisum.pigeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022-01-12 02:52:58 PM Wed
 */
@SpringBootApplication
public class StartupApplication {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        SpringApplication.run(StartupApplication.class, args);
    }
}
