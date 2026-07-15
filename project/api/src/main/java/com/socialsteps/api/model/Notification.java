package com.socialsteps.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne
    private User sender;

    private String message;

    @ManyToOne
    private User recipient;

    //persistence constructor
    public Notification(){ }

    //friend invite constructor
    public Notification(User sender, User recipient){
        this.type = NotificationType.FRIENDREQ;
        this.sender = sender;
        this.recipient = recipient;
        this.recipient.addNotification(this);
    }

    public Long getId(){
        return this.id;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setRecipient(User recipient){
        this.recipient = recipient;
    }

    public void setType(NotificationType type){
        this.type = type;
    }
}
