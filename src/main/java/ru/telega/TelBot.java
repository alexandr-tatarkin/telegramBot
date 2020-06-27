package ru.telega;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelBot implements LongPollingBot {

    @Value("${telega.bot-token}")
    private String botToken;

    @Value("${telega.bot-name}")
    private String botName;

    private final Config config;
    private final DefaultAbsSender sender;
    private static final TelBotMethodContainer container = TelBotMethodContainer.getInstance();

    @Override
    public void onUpdateReceived(Update update) {
        if (config.getChats() != null && !config.getChats().contains(update.getMessage().getChatId())) return;
        if (update.hasMessage()) {
            String key = "";
            if (update.getMessage().isCommand())
                key = update.getMessage().getText().split(" ")[0].trim();
            else if (update.getMessage().hasText())
                key = ContentType.TEXT.toString();
            else if (update.getMessage().hasSticker())
                key = ContentType.STICKER.toString();
            TelBotMethodController controller = container.getController(key);
            try {
                sender.execute(controller.process(update));
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public BotOptions getOptions() {
        return sender.getOptions();
    }

    @Override
    public void clearWebhook() {
    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            log.error(e.getMessage());
        }
    }

}