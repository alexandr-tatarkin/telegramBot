### Prerequisites
 Following properties are mandatory:
```
telega.bot-token=<bot token>
telega.bot-name=<bot name>
```
 access to your bot can be restricted with the property:
```
telega.chats=<alowed chats>
```
 necessary dependency and repositories:
 
    <repositories>
       <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
       </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.github.alexandr-tatarkin</groupId>
            <artifactId>telegramBot</artifactId>
            <version>v0.0.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.telegram</groupId>
            <artifactId>telegrambots</artifactId>
            <version>4.9</version>
        </dependency>
    </dependencies>


### Example

```java
@TelBotController
public class ControllerExample {

    @TelBotRequestMapping(value = "/ok")
    public SendMessage ok(Update update) {
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("okay!");
    }
}
```
