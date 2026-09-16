package com.sciatta.java.spring.context.message.impl;

import com.sciatta.java.spring.context.message.MessageTypeEnum;
import com.sciatta.java.spring.context.message.MessageService;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiaoyu on 2026/9/15<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * 英文消息服务实现
 */
@Service
public class EnMessageServiceImpl implements MessageService {
    @Override
    public MessageTypeEnum getType() {
        return MessageTypeEnum.EN;
    }

    @Override
    public String getMessage() {
        return "Hello!";
    }
}
