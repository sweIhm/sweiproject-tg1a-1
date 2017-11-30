package edu.hm.cs.se.activitymeter.controller.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class GMailAuthenticator extends Authenticator {

    private String user;
    private String pw;

    public GMailAuthenticator (String username, String password) {
        super();
        this.user = username;
        this.pw = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pw);
    }
}