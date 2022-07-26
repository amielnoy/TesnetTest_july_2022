package Tests;

import Utils.AllureUtils;
import Utils.Email.EmailUtils;
import Utils.PropertiesUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static io.qameta.allure.Allure.step;


public class GmailTest {
    @Test
    public void TestGmailMessage() throws Exception {
        String password = "a1m2i3s4";
        MimeMessage message;

        Properties props = PropertiesUtil.getEmailProperties();
        // Recipient's email ID needs to be mentioned.
        String to = "amiel.noyfeld@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "amiel.noyfeld@gmail.com";

        // Assuming you are sending email from through gmails smtp
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // Get the Session object.// and pass username and password
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }finally {
          session=null;
          message=null;
        }

        EmailUtils currEmailUtils = new EmailUtils(
                props.getProperty("mail.username"),
                props.getProperty("mail.password"),
                props.getProperty("mail.smtp.host"),
                EmailUtils.EmailFolder.INBOX, props, "imaps");
        int numOfMailsBefore = currEmailUtils.getNumberOfMessages();
        //EMAIL VERIFICATION
        javax.mail.Message latestMessage = null;

        //String schduledMailDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String strInMailSubject = "IMS Email Delivery - amiel.noyfeld@scrdairy.com ";


        Message[] foundMailMessages = currEmailUtils.getMessagesBySubject(strInMailSubject, false, 100);

        for (Message mailMessage : foundMailMessages) {
            //AllureUtils.reportToLog4jAndAllure(mailMessage.getSubject(), LOGGER);
            //if(mailMessage.getReceivedDate().compareTo())
        }
        String schduledMailDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Assert.assertTrue(foundMailMessages.length > 0
                , "the message with string in subject=" + strInMailSubject + "\nFAILED TO BE DETECTED  in Inbox");

        Assert.assertTrue(foundMailMessages[foundMailMessages.length - 1].getSubject().contains(strInMailSubject + schduledMailDate)
                , "failed to found message with subject \n" + strInMailSubject + " " + schduledMailDate);
        //AllureUtils.reportToLog4jAndAllure("Message with subject:" + strInMailSubject + " Arrived" + "\n\n", LOGGER);


    }
}
