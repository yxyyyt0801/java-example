package com.sciatta.java.spring.context.message.impl;

import com.sciatta.java.spring.context.message.MessageTypeEnum;
import com.sciatta.java.spring.context.message.MessageService;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiaoyu on 2026/9/14<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * 中文消息服务实现
 */
@Service
public class ChMessageServiceImpl implements MessageService {
    @Override
    public MessageTypeEnum getType() {
        return MessageTypeEnum.CH;
    }

    @Override
    public String getMessage() {
        return "你好!";
    }
}
