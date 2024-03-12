package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.SmsMessage;
import io.javabrains.ratingsdataservice.repository.MessageRepository;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    // como se declara un argumentCaptor =
    @Captor
    private ArgumentCaptor<SmsMessage> smsMessageArgumentCaptor;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;


    // test 1
    // mandar id como 0
    // verificar excepcion con mensaje
    // verificar no llamados a messageRepository
    @Test
    void test1() {
        int messageId = 0;

        assertThrows(IllegalArgumentException.class, () -> messageService.getMessageById(messageId),
                "id of the message cannot be 0!");

        verifyNoInteractions(messageRepository);
    }


    // test 2
    // mandar id que NO sea 0
    // usar when para que messageRepository.getMessageById devuelva un valor
    // comparar valor retornado de este metodo
    // verificar llamado a getMessageById
    // verificar no mas llamados
    @Test
    void test2() {
        // given 2
        int messageId = 3;
        SmsMessage smsMessage = new SmsMessage();
        when(messageRepository.getMessageById(messageId)).thenReturn(smsMessage);

        // when 1
        SmsMessage value = messageService.getMessageById(messageId);

        // then 3
        assertEquals(smsMessage, value);

        verify(messageRepository, times(1)).getMessageById(messageId);
        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    void test3() {
        // test 1
        // mandar id como 0
        // verificar excepcion con mensaje
        // verificar no llamados a messageRepository
        int messageId = 0;
        // comparando una excepcion con mensaje
        assertThrows(IllegalArgumentException.class, () -> messageService.deleteMessage(messageId),
                "id of the message cannot be 0!");

        verifyNoInteractions(messageRepository);

    }

    @Test
    void test4() {
        // test 2
        // mandar id que NO sea 0
        // verificar llamado a deleteMessage
        // verificar no mas llamados

        // given 2
        int id = 1;
        // when 1
        // hacemos el llamado
        messageService.deleteMessage(id);

        // solo verificamos un llamado
        verify(messageRepository, times(1)).deleteMessage(id);
        // verificamos no mas llamados, despues del de arriba, que no haya mas llamados
        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    void test5() {
        // test 1
        // mandar un message
        // verificar llamado a sendMessage
        // verificar no mas llamados
        // verificar con argument captor que el valor enviado a sendMessage tiene un sender como SYSTEM
        // given
        SmsMessage smsMessage = new SmsMessage(2, "Hola", "Stephanie");

        // when
        messageService.sendMessageWithSystemSender(smsMessage);

        // then
//        verify(messageRepository, times(1)).sendMessage(smsMessage);
        // verificamos un llamado y capturamos el SmsMessage con un argument captor
        verify(messageRepository, times(1)).sendMessage(smsMessageArgumentCaptor.capture());
        // obtenemos el valor del argument captor, en este caso es un SmsMessage
        SmsMessage messageCaptorValue = smsMessageArgumentCaptor.getValue();
        assertEquals("SYSTEM", messageCaptorValue.getSender());

        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    void test6() {
        // tipo de variable _ nombre de la variable
        SmsMessage message = new SmsMessage(2, "Hola", "Stephanie");
        SmsMessage message2 = new SmsMessage(2, "Hola", "Stephanie");

        System.out.println(message.hashCode());
        System.out.println(message2.hashCode());

        // imprimir en consola 'System.out.println'
        // llamar un get o set se escribe primero el nombre de la variable un punto y llamamos al metodo
        System.out.println(message.getSender());

        // puedo comparar tipos de dato String, int, double, objectos, lo que sea
        // con control + p puede verificar que necesita el metodo
        assertEquals("Stephanie", message.getSender());
        assertEquals(2, message.getId());

        // si quiero comparar exceptions usamos assertThrows
        //assertThrows(Exception.class, () -> nombreDelMetodoAlQueNosVaATirarUnaExcepcion, "MENSAJE")
        assertThrows(Exception.class, () -> messageService.deleteMessage(0), "id of the message cannot be 0!");

        // si quiero comparar un boolean true or false
        //assertTrue(message.isRelated());
        assertFalse(message.isRelated());

        // si quiero verificar que el campo es null
        assertNull(null);

        // si quiero verificar que el campo NO es null
        assertNotNull(6);

        // siempre es igual  verify(messageRepository, times(1)).
        verify(messageRepository, times(1)).deleteMessage(0);
    }


    @Test
    void test7() {
        // test 1
        // message id es null
        // usar when para que messageRepository.getNewId devuelva un valor
        // verificar llamados a getNewId y saveMessage
        // verificar no mas llamados
        // verificar con argument captor que el valor enviado a saveMessage tiene un id (no es null)
        int messageId = 5;
        SmsMessage smsMessage = new SmsMessage();

        assertNull(smsMessage.getMessage());

        smsMessage.setMessage("");

        assertNotNull(smsMessage.getMessage());

        //given
        when(messageRepository.getNewId()).thenReturn(messageId);

        // primitives
        int ejemplo = 123;
        //double float char
        // int ejemplo = null; NO puede ser null porque int es primitivo

        // wrapper classes
        Integer ejemploWrapper = null; // pueden ser null
//        Double
//        Float
//        Character

        //when
        messageService.saveNewMessage(smsMessage);

        //then
        verify(messageRepository, times(1)).saveMessage(smsMessageArgumentCaptor.capture());
        SmsMessage messageCaptorValue = smsMessageArgumentCaptor.getValue();
        assertEquals(messageId, messageCaptorValue.getId());

        verify(messageRepository, times(1)).getNewId();
        verifyNoMoreInteractions(messageRepository);

    }

    @Test
    void test8() {
        // test 2
        // message id NO es null
        // verificar excepcion con mensaje
        // verificar no llamados a messageRepository

        int messageId = 4;
        SmsMessage smsMessage = new SmsMessage(4, "", "");
        smsMessage.setId(4);

        assertThrows(IllegalArgumentException.class, () -> messageService.saveNewMessage(smsMessage),
                "id of the message must be null");

        verifyNoInteractions(messageRepository);
    }

    @Test
    void test9() {
        // test 1
        // message id NO es null
        // usar when para que messageRepository.getMessageById devuelva un valor
        // verificar llamados a saveMessage y getMessageById
        // verificar no mas llamados
        int messageId = 5;
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setId(messageId);

        //given
        when(messageRepository.getMessageById(messageId)).thenReturn(smsMessage);

        //when
        messageService.saveExistingMessage(smsMessage);

        //then
        verify(messageRepository, times(1)).saveMessage(smsMessage);
        verify(messageRepository, times(1)).getMessageById(messageId);
        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    void test10() {
        // test 2
        // message id es null
        // verificar excepcion con mensaje
        // verificar no llamados a messageRepository
        SmsMessage smsMessage = new SmsMessage();

        assertThrows(IllegalArgumentException.class, () -> messageService.saveExistingMessage(smsMessage),
                "id of the message cannot be null!");

        verifyNoInteractions(messageRepository);
    }

    @Test
    void test11() {
        // test 3
        // message id NO es null
        // no usar when para que messageRepository.getMessageById sea null y retorne RuntimeException
        // verificar excepcion con mensaje
        // verificar llamados a getMessageById
        // verificar no mas llamados

        int messageId = 3;
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setId(messageId);


//given

//        when(messageRepository.getMessageById(messageId)).thenReturn(smsMessage);
//when
        assertThrows(RuntimeException.class, () -> messageService.saveExistingMessage(smsMessage),
                "message does not exist in the database");
//then
        verify(messageRepository, times(1)).getMessageById(messageId);
        verifyNoMoreInteractions(messageRepository);
    }
}

