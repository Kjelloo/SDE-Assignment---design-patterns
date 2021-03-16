/**
 * @author kjell
 */

package com.redheads.patterns.bll;

import com.redheads.patterns.be.Message;
import com.redheads.patterns.dal.IMessageRepository;
import com.redheads.patterns.dal.MessageRepository;

import java.util.List;

public class MessageCreatorFacade implements IMessageFacade {

    private IMessageRepository repo;
    private static MessageCreatorFacade inst;

    private MessageCreatorFacade() {
        repo = new MessageRepository();

    }

    public static IMessageFacade getInstance() {
        if(inst == null){
            inst = new MessageCreatorFacade();
        }
        return inst;
    }

    @Override
    public Message logMessage(String msg) {
        return repo.sendMessage(msg);
    }

    @Override
    public List<Message> getAllMessages() {
        return repo.getAll();
    }

}
