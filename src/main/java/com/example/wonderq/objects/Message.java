package com.example.wonderq.objects;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Created by shallinris on 08/03/2019.
 */
@Entity @Table(name = "MESSAGES")
public class Message implements Serializable, Cloneable {


    public static enum MsgStatus { UNREAD, PENDING, PROCESSED};
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    private String message;
    private MsgStatus status;
    @Column(name="WASPROCESSED")
    private boolean wasProcessed;
    private ZonedDateTime publishDate;

    public Message(String message, MsgStatus status, boolean wasProcessed, ZonedDateTime publishDate) {
        this.message = message;
        this.status = status;
        this.wasProcessed = wasProcessed;
        this.publishDate = publishDate;
    }

    public Message() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setStatus(MsgStatus status) { this.status = status;}
    public MsgStatus getStatus() { return status; }
    public void setWasProcessed(boolean wasProcessed) {
        this.wasProcessed = wasProcessed;
    }
    public boolean getWasProcessed() {
        return wasProcessed;
    }
    public void setPublishDate(ZonedDateTime publishDate) {
        this.publishDate = publishDate;
    }
    public ZonedDateTime getPublishDate() {
        return publishDate;
    }
}
