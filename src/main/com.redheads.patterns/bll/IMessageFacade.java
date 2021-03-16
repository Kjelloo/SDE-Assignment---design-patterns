package com.redheads.patterns.bll;

import com.redheads.patterns.be.Message;

import java.util.List;

public interface IMessageFacade {
    Message logMessage(String msg);
    List<Message> getAllMessages();
}
