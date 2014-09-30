package com.sputnik.messages;

import com.sputnik.persistence.Message;
import com.sputnik.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessagesController {
    private final MessageRepository messageRepository;

    @Autowired
    public MessagesController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/messages")
    public @ResponseBody
    Message createMessage(@RequestBody Message message){
        Message savedMessage = messageRepository.save(message);
        return savedMessage;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages/{id}")
    public @ResponseBody
    Message getMessage(@PathVariable Long id){
        return messageRepository.findOne(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/messages/{id}")
    public void deleteMessage(@PathVariable Long id){
        messageRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages")
    public @ResponseBody
    Iterable<Message> getAllMessages(){
        return messageRepository.findAll();
    }
}
