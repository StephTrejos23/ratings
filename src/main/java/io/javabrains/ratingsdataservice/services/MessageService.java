package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.SmsMessage;
import io.javabrains.ratingsdataservice.repository.MessageRepository;

public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // test 1
    // mandar id como 0
    // verificar excepcion con mensaje
    // verificar no llamados a messageRepository

    // test 2
    // mandar id que NO sea 0
    // usar when para que messageRepository.getMessageById devuelva un valor
    // comparar valor retornado de este metodo
    // verificar llamado a getMessageById
    // verificar no mas llamados
    public SmsMessage getMessageById(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("id of the message cannot be 0!");
        }

        return messageRepository.getMessageById(id);
    }

    // test 1
    // mandar id como 0
    // verificar excepcion con mensaje
    // verificar no llamados a messageRepository

    // test 2
    // mandar id que NO sea 0
    // verificar llamado a deleteMessage
    // verificar no mas llamados
    public void deleteMessage(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("id of the message cannot be 0!");
        }

        messageRepository.deleteMessage(id);
    }

    // test 1
    // mandar un message
    // verificar llamado a sendMessage
    // verificar no mas llamados
    // verificar con argument captor que el valor enviado a sendMessage tiene un sender como SYSTEM
    public void sendMessageWithSystemSender(SmsMessage smsMessage ) { // (tipo de variable _ nombre de la variable)
        SmsMessage newSmsMessage = new SmsMessage(); // crea una nueva variable
        newSmsMessage.setId(smsMessage.getId()); // usamos el metodo set id para que la variable newSmsMessage tenga el id de smsMessage
        newSmsMessage.setMessage(smsMessage.getMessage());
        newSmsMessage.setSender("SYSTEM");
        messageRepository.sendMessage(newSmsMessage);
    }

    // test 1
    // message id es null
    // usar when para que messageRepository.getNewId devuelva un valor
    // verificar llamados a getNewId y saveMessage
    // verificar no mas llamados

    // test 2
    // message id NO es null
    // verificar excepcion con mensaje
    // verificar no llamados a messageRepository
    public void saveNewMessage(SmsMessage smsMessage) {
        if (smsMessage.getId() != null) {
            throw new IllegalArgumentException("id of the message must be null!");
        }

        smsMessage.setId(messageRepository.getNewId());

        messageRepository.saveMessage(smsMessage);
    }

    // test 1
    // message id NO es null
    // usar when para que messageRepository.getMessageById devuelva un valor
    // verificar llamados a saveMessage y getMessageById
    // verificar no mas llamados

    // test 2
    // message id es null
    // verificar excepcion con mensaje
    // verificar no llamados a messageRepository

    // test 3
    // message id NO es null
    // no usar when para que messageRepository.getMessageById sea null y retorne RuntimeException
    // verificar excepcion con mensaje
    // verificar llamados a getMessageById
    // verificar no mas llamados
    public void saveExistingMessage(SmsMessage smsMessage) {
        if (smsMessage.getId() == null) {
            throw new IllegalArgumentException("id of the message cannot be null!");
        }

        if (messageRepository.getMessageById(smsMessage.getId()) == null) {
            throw new RuntimeException("message does not exist in the database");
        }

        messageRepository.saveMessage(smsMessage);
    }
}