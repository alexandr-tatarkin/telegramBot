package com.example.telega;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
@Slf4j
@EnableScheduling
@EnableConfigurationProperties
public class TelegaApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegaApplication.class, args);
    }

}
