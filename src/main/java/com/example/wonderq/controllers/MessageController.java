package com.example.wonderq.controllers;

import com.example.wonderq.dao.MessageRepository;
import com.example.wonderq.objects.Message;
import com.example.wonderq.objects.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shallinris on 08/03/2019.
 */
@RestController @RequestMapping(value= {"/messages"})
public class MessageController {

    @Autowired
    MessageRepository messageRep;

    //this api returns all messages (think of this as the dirty developer tool that shows the status of WonderQ
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Message> findAll(Pageable pageable) {
        return messageRep.findAll();
    }


    //this api returns all messages where the value of wasProcessed = false (aka all messages available to consumers)
    @RequestMapping(method = RequestMethod.GET, value="/new")
    public @ResponseBody List<Message> findAllAvailableMessages(Pageable pageable){
        Iterable<Message> all = messageRep.findAll();
        List<Message> allUnprocessed = new ArrayList<>();
        for (Message msg: all) {
            if (msg.getWasProcessed() == false) {
                allUnprocessed.add(msg);
            }
        }
        return allUnprocessed;
    }

    //returns generated id as confirmation
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody int publishMessage(@RequestBody Message message) {
        System.out.println("got to post");
        Status status = new Status();
        try {
            if (message.getId() == 0) {
                message.setStatus(Message.MsgStatus.UNREAD);
                messageRep.save(message);
                status.setSuccess(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            status.setSuccess(false);
        }
        return message.getId();
    }

    @RequestMapping(method = RequestMethod.PUT, value="{id}")
    public @ResponseBody Message updateMessage(@RequestBody Message message) {
        Status status = new Status();
        try {
            if (message.getId() > 0) {
                messageRep.save(message);
                status.setSuccess(true);
            }
        } catch(Exception e) {
            e.printStackTrace();
            status.setSuccess(false);
        }
        return message;
    }

    //todo: maybe add status for all of these
    @RequestMapping(method = RequestMethod.DELETE, value="{id}")
    public @ResponseBody Status deleteMessage(@PathVariable Integer id) {
        Status status = new Status();
        try {
            messageRep.deleteById(id);
            status.setSuccess(true);
        } catch(Exception e) {
            e.printStackTrace();
            status.setSuccess(false);
        }
        return status;
    }

    /**
     * runs at a fixed rate of every 30 minutes. It iterates through all messages and deletes the ones with a status
     * set to ‘PROCESSED’(this value is set on the front end when a user ticks the checkbox next to the message.
     */
    @Scheduled(fixedRate = 1800000)
    public void deleteAllProcessedMessages() {
        Iterable<Message> all = messageRep.findAll();
        for (Message msg: all) {
            if (msg.getStatus() == Message.MsgStatus.PROCESSED){
                messageRep.delete(msg);
            }
        }
    }
}
