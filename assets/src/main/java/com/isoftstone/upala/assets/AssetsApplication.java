package com.isoftstone.upala.assets;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.isoftstone.upala.assets.mapper")
@Log4j2
public class AssetsApplication
{

    public static void main(String[] args)
    {
        log.debug("SpringBoot start...");
        SpringApplication.run(AssetsApplication.class, args);
        log.debug("SpringBoot ended...");
    }

}

