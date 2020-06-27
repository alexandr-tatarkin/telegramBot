### Prerequisites
 Following properties are mandatory:
```
telega.bot-token=<bot token>
telega.bot-name=<bot name>
```
 access to this bot could be restricted with the property:
```
telega.chats=<alowed chats>
```
 necessary dependency:
 
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter</artifactId>
         </dependency>
         <dependency>
             <groupId>org.telegram</groupId>
             <artifactId>telegrambots</artifactId>
             <version>4.9</version>
         </dependency>


### Example

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
