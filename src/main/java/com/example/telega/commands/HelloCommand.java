package com.example.telega.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/log")
@Slf4j
public class HelloCommand implements TCommandAuthorized {

    @Override
    public void execute(Update update) {
        log.info(update.getMessage().getText());
    }
}
