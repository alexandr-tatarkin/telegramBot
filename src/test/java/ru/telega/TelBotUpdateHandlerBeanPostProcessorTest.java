package ru.telega;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes={ru.telega.Config.class})
class TelBotUpdateHandlerBeanPostProcessorTest {

    @MockBean
    private TelBot mockBot;

    @Autowired
    private TelBotUpdateHandlerBeanPostProcessor postProcessor;


    @Test
    public void annotationTest () {
        assertTrue(postProcessor.getContainer().hasController("/test"));
    }

}