package com.github.liyue2008.spider.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by liyue on 2017/3/30.
 */
@Configuration
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties

public class AppConfig {

    @Value("${spider.worker-thread-count:10}")
    private int workerThreadCount;

    @Value("${spider.proxy-test-thread-count:20}")
    private int proxyThreadCount;

    @Bean(name ="jobExecutor")
    public ThreadPoolTaskExecutor getJobExecutor() {
        ThreadPoolTaskExecutor executor;
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(workerThreadCount);
        executor.setMaxPoolSize(workerThreadCount);
        executor.setQueueCapacity(65535);
        executor.setThreadNamePrefix("Job-");
        executor.initialize();
        return executor;
    }

    @Bean(name ="proxyExecutor")
    public ThreadPoolTaskExecutor getProxyExecutor() {
        ThreadPoolTaskExecutor executor;
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(proxyThreadCount);
        executor.setMaxPoolSize(proxyThreadCount);
        executor.setQueueCapacity(65535);
        executor.setThreadNamePrefix("Proxy-");
        executor.initialize();
        return executor;
    }

    @Bean(name ="taskExecutor")
    public ThreadPoolTaskExecutor getSpiderExecutor() {
        ThreadPoolTaskExecutor executor;
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("Spider-");
        executor.initialize();
        return executor;
    }






}
