package com.ocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ltx
 */
@SpringBootApplication(scanBasePackages = "com.ocean")
public class OceanAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(OceanAdminApplication.class,args);
    }
}
