package com.sputnik.messages;

import com.sputnik.persistence.Message;
import com.sputnik.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessagesController {
    private final MessageRepository messageRepository;

    @Autowired
    public MessagesController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method= RequestMethod.GET, value ="/messages/{title}")
    public @ResponseBody
    List<Message> getMessage(@PathVariable String title, Model model){
        return messageRepository.findByTitle(title);
    }
    @RequestMapping(method= RequestMethod.GET, value ="/messages")
    public @ResponseBody
    Iterable<Message> getMessage(Model model){
        return messageRepository.findAll();
    }
}
