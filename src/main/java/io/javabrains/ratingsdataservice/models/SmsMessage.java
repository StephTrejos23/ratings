package io.javabrains.ratingsdataservice.models;

public class SmsMessage
{
    private Integer id;
    private String message;
    private String sender;
    private boolean related;

    // constructor vacio, puede crear un nuevo object como new SmsMessage()
    public SmsMessage()
    {
    }

    // constructor con campos, puede crear un nuevo object new SmsMessage(valores...)
    public SmsMessage( Integer id, String message, String sender )
    {
        this.id = id;
        this.message = message;
        this.sender = sender;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender( String sender )
    {
        this.sender = sender;
    }

    public boolean isRelated() {
        return related;
    }

    public void setRelated(boolean related) {
        this.related = related;
    }
}