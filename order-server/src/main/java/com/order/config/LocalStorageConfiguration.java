package com.order.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.order.properties.LocalStorageProperties;
import com.order.utils.LocalStorageUtil;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LocalStorageConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LocalStorageUtil localStorageUtil(LocalStorageProperties localStorageProperties) {
        log.info("LocalStorageConfiguration: {}", localStorageProperties);
        return new LocalStorageUtil(localStorageProperties.getLocation(), localStorageProperties.getBaseUrl());
    }
    

}
