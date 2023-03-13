package com.example.demo9.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
@PropertySource("classpath:.env")
@RequiredArgsConstructor
public class MailSenderConfig {

    @Value(value = "${spring.mail.host}")
    private String host;
    @Value(value = "${spring.mail.port}")
    private int port;
    @Value(value = "${spring.mail.username}")
    private String username;
    @Value(value = "${spring.mail.password}")
    private String password;
    @Value(value = "${spring.mail.properties.mail.smtp.auth}")
    private String auth;
    @Value(value = "${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

    public void configAndSendMail(SimpleMailMessage mail) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", "false");
        mailSender.send(mail);
    }

}
