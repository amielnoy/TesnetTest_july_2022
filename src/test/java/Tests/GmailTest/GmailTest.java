package Tests.GmailTest;

import Utils.Email.EmailUtils;
import Utils.PropertiesUtil;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static io.qameta.allure.Allure.step;

public class GmailTest {
    /**
     * Test downloading e-mail messages
     */
    //@Test
    public void readMail() {
        // for POP3
        //String protocol = "pop3";
        //String host = "pop.gmail.com";
        //String port = "995";

        // for IMAP
        String protocol = "imap";
        String host = "imap.gmail.com";
        String port = "993";


        String userName = "amiel.noyfeld@gmail.com";
        String password = "ixuebaayghditgfc";

        ImapAndPop3ReadMailUtil currentImapAndPop3ReadMailUtil=new ImapAndPop3ReadMailUtil();
        currentImapAndPop3ReadMailUtil.downloadEmails(protocol, host, port, userName, password);
    }
    //@Test
    public void readMailPop3() {
        try {
            String password = "ixuebaayghditgfc";
            String host = "imap.gmail.com";
            String user = "amiel.noyfeld@gmail.com";
            //create properties field
            // create properties
            Properties properties = new Properties();
            properties.put("mail.debug", "true");
            properties.put("mail.store.protocol", "imap");
            properties.put("mail.imap.host", "imap.gmail.com");
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.timeout", "10000");

            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    user, password);// Specify the Username and the PassWord
                        }
                    });
            IMAPStore store ;
            Folder inbox ;

            try {
                store = (IMAPStore) session.getStore("imaps");
                store.connect(host,user, password);

                inbox = (IMAPFolder) store.getFolder("INBOX");

            } catch (NoSuchProviderException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }catch (Exception e){
        e.printStackTrace();}
    }

    @Test
    public void sendMailSmtpPort587() throws Exception {
        String m_subject = "My Subject Smtp port 587 mail",
                m_text = "This message is from Indoor Positioning App. Required file(s) are attached.";

        String username = "amiel.noyfeld@gmail.com";
        String d_host = "smtp.gmail.com";
        String password1 = "a1m2i3!@";
        String password2 = "buvwfarcqrubwidn";
        String d_port = "587";
        String recipients = "amiel.noyfeld@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols","TLSv1.2");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password2);
                    }
                }
        );

        session.setDebug(true);
//        Store store =
//                session.getStore(new URLName("imap://amiel.noyfeld@gmail.com:"+password+"@smtp.gmail.com:"
//                        + 143 + "/INBOX"));
        Store store = session.getStore("pop3s");//"pop3s"
        store.connect();
        Folder inbox = store.getFolder("INBOX"); //actually i need "SENT"
        if (inbox == null) {
           System.out.println("No INBOX");
            System.exit(1);
        }
       inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        for (int i = 0; i < messages.length; i++) {
            System.out.println("Message " + (i + 1));
            messages[i].writeTo(System.out);
        }
        inbox.close(false);
        store.close();


        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(username));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(username));

            Transport transport = session.getTransport("smtp");
            transport.connect(d_host, Integer.valueOf(d_port), username, password2);

            //transport.sendMessage(msg, msg.getAllRecipients());
            //transport.close();

        } catch (AddressException e) {
            e.printStackTrace();

        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }
    //@Test
    public void TestSendMailMessage(){
        Properties props = new Properties();

        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.from", "amiel.noyfeld@gmail.com");
        props.put("mail.to", "amiel.noyfeld@gmail.com");
        props.put("mail.password", "a1m2i3s4");
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.enable", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        Session session = Session.getInstance(props, null);
        SmtpAuthenticator authentication = new SmtpAuthenticator();

        try {
            javax.mail.Message msg = new MimeMessage(Session
                    .getDefaultInstance(props, authentication));
            msg.setFrom();
            Address[] addresses=new Address[1];
            addresses[0]= new InternetAddress( "amiel.noyfeld@gmail.com" );
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("amielnoy@gmail.com, amielnoy@outlook.com"));
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            msg.setText("Hello, world!\n");
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
    //@Test
    public void TestGmailMessage() throws Exception {
        String password = "a1m2i3s4";
        //MimeMessage message;

        Properties props = PropertiesUtil.getEmailProperties();
        // Recipient's email ID needs to be mentioned.
        String to = "amiel.noyfeld@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "amiel.noyfeld@gmail.com";
        String reciverEmail=from;
        //props.put("mail.smtp.host", host);
        //props.put("mail.smtp.port","995");
        //IMAP_SSL
        //props.put("mail.smtp.port","993");
        //props.put("mail.smtp.port","587");
        //props.put("mail.smtp.port", "465");
        //IMAP_TLS
        //props.put("mail.smtp.port","143");
        //props.put("mail.smtp.ssl.enable", "true");
        //props.put("mail.smtp.auth", "true");

        // Set manual Properties
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        props.setProperty("mail.imap.ssl.enable", "true");
        //props.setProperty("mail.imap.starttls.enable", "true");

        //props.setProperty("ssl.SocketFactory.provider", "my.package.name.ExchangeSSLSocketFactory");
        //props.setProperty("mail.imap.socketFactory.class", "my.package.name.ExchangeSSLSocketFactory");

        //props.setProperty("mail.imaps.socketFactory.class", SSL_FACTORY);
        //props.setProperty("mail.imaps.socketFactory.fallback", "false");
        //props.setProperty("mail.imap.ssl.enable", "true");
        //props.setProperty("mail.imap.port", "993");
        //props.setProperty("mail.imaps.socketFactory.port", "993");
        //props.put("mail.imap.host", "outlook.office365.com");

        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");

        try {
            EmailUtils currEmailUtils = new EmailUtils(
                    props.getProperty("mail.username"),
                    props.getProperty("mail.password"),
                    props.getProperty("mail.imap.host"),
                    EmailUtils.EmailFolder.INBOX, props, "imap");
            Message[] latestMessages = new Message[0];
            Message latestMessage = null;
            for (int delayNumber = 1; delayNumber <= 15; ++delayNumber) {
                Thread.sleep(2000);
                latestMessages =
                        currEmailUtils.getMessagesBySubject(reciverEmail
                                , false, 1);
                if (latestMessages.length > 0) {
                    latestMessage = latestMessages[latestMessages.length - 1];
                    if (latestMessage.getSubject().contains(reciverEmail))
                        break;
                }
                if (latestMessage != null)
                    Assert.assertTrue( latestMessage.getSubject().contains(reciverEmail) , "mail message not found!!");
            }
            if (latestMessage != null)
                Assert.assertTrue(latestMessage.getSubject()!=null);
            Assert.assertTrue(latestMessage!=null,"the message with cow number=" + reciverEmail + "\\nFAILED TO BE DETECTED  in Inbox");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(2);
        }


//        EmailUtils currEmailUtils = new EmailUtils(
//                props.getProperty("mail.username"),
//                props.getProperty("mail.password"),
//                props.getProperty("mail.smtp.host"),
//                EmailUtils.EmailFolder.INBOX, props, "imaps");
//        int numOfMailsBefore = currEmailUtils.getNumberOfMessages();
//        //EMAIL VERIFICATION
//        javax.mail.Message latestMessage = null;
//
//        //String schduledMailDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//        String strInMailSubject = "IMS Email Delivery - amiel.noyfeld@scrdairy.com ";
//
//
//        Message[] foundMailMessages = currEmailUtils.getMessagesBySubject(strInMailSubject, false, 100);
//
//        for (Message mailMessage : foundMailMessages) {
//            //AllureUtils.reportToLog4jAndAllure(mailMessage.getSubject(), LOGGER);
//            //if(mailMessage.getReceivedDate().compareTo())
//        }
//        String schduledMailDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//        Assert.assertTrue(foundMailMessages.length > 0
//                , "the message with string in subject=" + strInMailSubject + "\nFAILED TO BE DETECTED  in Inbox");
//
//        Assert.assertTrue(foundMailMessages[foundMailMessages.length - 1].getSubject().contains(strInMailSubject + schduledMailDate)
//                , "failed to found message with subject \n" + strInMailSubject + " " + schduledMailDate);
//        //AllureUtils.reportToLog4jAndAllure("Message with subject:" + strInMailSubject + " Arrived" + "\n\n", LOGGER);


    }
    //@Test
    public void testOutLokkMailMessage() throws MessagingException {
        final String userName = "amielnoy@outlook.com";
        final String password = "a1m2i3s8";
        final String hostImapOrPop3 = "outlook.office365.com";
        final String hostSmtp = "smtp.office365.com";
        final String host = "outlook.office365.com";
        // properties
        Properties props = System.getProperties();

        //props.put("mail.smtp.host", host);
        //props.put("mail.smtp.port","995");
        //IMAP_SSL
        //props.put("mail.smtp.port","993");
        //props.put("mail.smtp.port","587");
        //props.put("mail.smtp.port", "465");
        //IMAP_TLS
        //props.put("mail.smtp.port","143");
        //props.put("mail.smtp.ssl.enable", "true");
        //props.put("mail.smtp.auth", "true");

        // Set manual Properties
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        //props.setProperty("mail.imap.ssl.enable", "true");
        //props.setProperty("mail.imap.starttls.enable", "true");

        //props.setProperty("ssl.SocketFactory.provider", "my.package.name.ExchangeSSLSocketFactory");
        //props.setProperty("mail.imap.socketFactory.class", "my.package.name.ExchangeSSLSocketFactory");

        //props.setProperty("mail.imaps.socketFactory.class", SSL_FACTORY);
        //props.setProperty("mail.imaps.socketFactory.fallback", "false");
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.imap.port", "993");
        //props.setProperty("mail.imaps.socketFactory.port", "993");
        props.put("mail.imap.host", "outlook.office365.com");

        try {

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(userName, password);
                        }
                    });
            session.setDebug(true);

            // Store object
            Store store = session.getStore("imap");
            store.connect(hostImapOrPop3, userName, password);

            // Get folder
            Folder folder = store.getFolder("Drafts");
            folder.open(Folder.READ_ONLY);
            Message messages[] = folder.getMessages();
            for (Message message : messages) {
                System.out.println(message.getSubject());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

