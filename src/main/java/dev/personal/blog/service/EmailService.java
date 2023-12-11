package dev.personal.blog.service;

import dev.personal.blog.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;
    private final TemplateEngine htmlTemplateEngine;
    private final TemplateEngine textTemplateEngine;

    public EmailService(JavaMailSender emailSender, @Qualifier(value = "html") TemplateEngine htmlTemplateEngine, @Qualifier(value = "text") TemplateEngine textTemplateEngine) {
        this.emailSender = emailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
        this.textTemplateEngine = textTemplateEngine;
    }

    public void sendSimpleMessage(String body, String subject, String recipient) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    public void sendTemplateMessage(Email email){
        String templateString = "This email was created using a template.\nDate of creation: " + LocalDateTime.now() + "\n\n---\n%s\n---\n\nWith best regards, Roman L.";
        String newBody = templateString.formatted(email.getEmailBody());
        sendSimpleMessage(newBody, email.getEmailSubject(), email.getRecipient());
    }

    public void sendHtmlMessage(String to, String subject, String body) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(from);

            Context context = new Context();
            context.setVariable("content", body);
            context.setVariable("name", "Dear Customer");
            context.setVariable("sign", "Roman L., CEO");
            String processedMessage = htmlTemplateEngine.process("greeting-template", context);

            helper.setText(processedMessage, true);

            emailSender.send(message);
        } catch (MessagingException e){
            System.out.println(e.getCause().getMessage());
        }
    }

    private File createAttachmentFromTemplate(String name, String amount, String product){
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("amount", amount);
        context.setVariable("product", product);
        String processedString = textTemplateEngine.process("check-template", context);


        File file = new File("C:\\Users\\roome\\Downloads\\blog\\blog\\src\\main\\resources\\checks\\emailtext.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(processedString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void sendMessageWithAttachment(String to, String subject, String body, String filePath){
        MimeMessage message = emailSender.createMimeMessage();
        ClassLoader classLoader = this.getClass().getClassLoader();
        //String fileName = createAttachmentFromTemplate("Customer", "10", "Cake");
        URL resource = classLoader.getResource("emailtext.txt");
        //String path = createAttachmentFromTemplate("Customer", "10", "Cake");
        //System.out.println(path);
        File file = createAttachmentFromTemplate("Customer", "10", "Cake");

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

