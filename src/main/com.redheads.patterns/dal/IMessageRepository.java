package com.redheads.patterns.dal;

import com.redheads.patterns.be.Message;

import java.util.List;

public interface IMessageRepository {
    Message sendMessage(String msg);
    Message getMessage(int id);
    List<Message> getAll();
}
