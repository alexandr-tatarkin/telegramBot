#Example 

```java
@TelBotController
public class ControllerExample {

    @TelegramRequestMapping(value = "/ok")
    public SendMessage ok(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("okay!");
    }


    @TelegramRequestMapping(content = ContentType.TEXT)
    public SendMessage text(Update update) {
            return new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("IDK");
    }

    @TelegramRequestMapping(content = ContentType.STICKER)
    public SendMessage sticker(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Nice sticker. BTW file id - " + update.getMessage().getSticker().getFileId());
    }

}
```