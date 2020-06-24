package com.example.telega.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component("/chatId")
@Slf4j
@RequiredArgsConstructor
public class GetIdsCommand implements TCommand {

    private final DefaultAbsSender sender;

    @Override
    public void execute(Update update) {
        SendMessage message =
                new SendMessage(update.getMessage().getChatId(), "Chat id - " + update.getMessage().getChatId());
        try {
            sender.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
