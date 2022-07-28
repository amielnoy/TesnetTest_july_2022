package Tests.GmailTest;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
    public SmtpAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "amiel.noyfeld@gmail.com";
        String password = "a1m2i3s4";
        //if ((username != null) && (username.length() > 0) && (password != null)
        //        && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        //}

        //return null;
    }
}
