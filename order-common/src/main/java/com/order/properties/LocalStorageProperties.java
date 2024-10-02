package com.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "order.local-storage")
@Data
public class LocalStorageProperties {

    private String location;

    private String baseUrl;

}
