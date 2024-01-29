package com.ocean.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ltx
 */
@Configuration
@ConfigurationProperties(prefix = "ocean.security")
@Data
public class SecurityProperty {
    private Boolean mockUser;
}
