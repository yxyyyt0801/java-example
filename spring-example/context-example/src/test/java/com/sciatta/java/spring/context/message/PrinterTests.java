package com.sciatta.java.spring.context.message;

import com.sciatta.java.spring.context.Starter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by yangxiaoyu on 2026/9/15<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * PrinterTests
 */
@SpringBootTest(classes = Starter.class)
public class PrinterTests {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testPrint() {
        Printer printer = context.getBean(Printer.class);
        printer.print();
    }

    @Test
    public void testPrintCh() {
        Printer printer = context.getBean(Printer.class);
        printer.print(MessageTypeEnum.CH);
    }

    @Test
    public void testPrintEn() {
        Printer printer = context.getBean(Printer.class);
        printer.print(MessageTypeEnum.EN);
    }

    @Test
    public void testNotSupportedMessageType() {
        Printer printer = context.getBean(Printer.class);

        assertThrows(IllegalArgumentException.class,
                () -> printer.print(MessageTypeEnum.FR));
    }

    @Test
    public void testMockSupported() {
        MessageService frMessageService = Mockito.mock(MessageService.class);
        when(frMessageService.getType()).thenReturn(MessageTypeEnum.FR);
        when(frMessageService.getMessage()).thenReturn("Bonjour!");

        Printer printer = new Printer(List.of(frMessageService));
        printer.print(MessageTypeEnum.FR);
    }

}
