package io.javabrains.ratingsdataservice.repository;
import io.javabrains.ratingsdataservice.models.SmsMessage;
import org.springframework.stereotype.Repository;

    @Repository
    public interface MessageRepository
    {
        int getNewId();

        void saveMessage( SmsMessage smsMessage );

        SmsMessage getMessageById(int id);

        void deleteMessage(int id);

        void sendMessage( SmsMessage smsMessage );
    }



