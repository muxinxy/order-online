package com.order.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.order.properties.LocalStorageProperties;
import com.order.utils.LocalStorageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 本地存储配置
 */
@Configuration
@Slf4j
public class LocalStorageConfiguration {

    /**
     * 本地存储工具类
     * @param localStorageProperties
     * @return LocalStorageUtil
     */
    @Bean
    @ConditionalOnMissingBean
    public LocalStorageUtil localStorageUtil(LocalStorageProperties localStorageProperties) {
        log.info("LocalStorageConfiguration: {}", localStorageProperties);
        return new LocalStorageUtil(localStorageProperties.getLocation(), localStorageProperties.getBaseUrl());
    }
    

}
