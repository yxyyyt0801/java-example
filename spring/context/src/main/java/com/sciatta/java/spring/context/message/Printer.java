package com.sciatta.java.spring.context.message;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoyu on 2026/9/14<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * Printer
 */
@Service
public class Printer {
    private final Map<MessageTypeEnum, MessageService> cache;

    public Printer(List<MessageService> messageService) {   // 构造函数注入 MessageService 接口的所有实现类
        cache = messageService.stream().collect(Collectors.toMap(
                m -> {
                    if (m == null) {
                        throw new IllegalArgumentException("MessageService 不可为 null");
                    }

                    MessageTypeEnum type = m.getType();
                    if (type == null) {
                        throw new IllegalArgumentException(
                                "MessageService " + m.getClass().getSimpleName()
                                        + "  消息类型不可为 null"
                        );
                    }
                    return type;
                },
                m -> m));
    }

    @PrinterTitleAnnotation(value = ">>>>>>>>>>>>>>>>>>", isNeed = true)
    public void print() {
        cache.forEach((messageTypeEnum, messageService) -> System.out.println(messageService.getMessage()));
    }

    @PrinterTitleAnnotation(value = ">>>>>>>>>>>>>>>>>>")
    public void print(MessageTypeEnum type) {
        cache.entrySet().stream()
                .filter(entry -> entry.getKey().equals(type))
                .findFirst()
                .ifPresentOrElse(entry -> System.out.println(entry.getValue().getMessage()),
                        () -> {
                            throw new IllegalArgumentException("不支持的消息类型: " + type);
                        });

    }
}
