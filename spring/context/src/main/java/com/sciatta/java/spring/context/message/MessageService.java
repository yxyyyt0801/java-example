package com.sciatta.java.spring.context.message;

/**
 * Created by yangxiaoyu on 2026/9/14<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * 消息服务接口
 */
public interface MessageService {
    MessageTypeEnum getType();

    String getMessage();
}
