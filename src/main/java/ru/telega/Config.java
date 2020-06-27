package ru.telega;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.util.List;

@Configuration
@ConfigurationProperties("telega")
@ComponentScan("ru.telega")
@EnableConfigurationProperties
@Getter
@Setter
public class Config {

    @Value("${telega.bot-token}")
    private String botToken;

    private List<Long> chats;

    @Bean
    public DefaultAbsSender sender() {
        return new DefaultAbsSender(new DefaultBotOptions()) {
            @Override
            public String getBotToken() {
                return botToken;
            }
        };
    }
}
