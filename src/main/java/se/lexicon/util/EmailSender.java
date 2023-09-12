package se.lexicon.util;

import se.lexicon.exception.EmailSendException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    //private static final String USERNAME = "EMAIL";
    //private static final String PASSWORD = "PASSWORD";

    public static void sendEmail(String recipient, String subject, String messageContent) {

        EmailConfig emailConfig = new EmailConfig();

        // set up email server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getEmail(), emailConfig.getPassword());
            }
        });


        try {
            Message message = new MimeMessage(session);
            Address myAddress = new InternetAddress(emailConfig.getEmail());

            message.setFrom(myAddress);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);


        } catch (AddressException e) {
           throw new EmailSendException("AddressException " + e.getMessage());
        } catch (MessagingException e) {
            throw new EmailSendException("MessagingException " + e.getMessage());
        }


    }


}
