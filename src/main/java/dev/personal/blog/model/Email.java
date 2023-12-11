package dev.personal.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Email {

    @JsonProperty("body")
    private String emailBody;

    @JsonProperty("subject")
    private String emailSubject;

    @Override
    public String toString() {
        return "Email{" +
                "emailBody='" + emailBody + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }

    @JsonProperty("recipient")
    private String recipient;

    public String getEmailBody() {
        return emailBody;
    }

    public Email(String emailBody, String emailSubject, String recipient) {
        this.emailBody = emailBody;
        this.emailSubject = emailSubject;
        this.recipient = recipient;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
