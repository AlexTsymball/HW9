package com.example.demo9;

import com.example.demo9.config.MailSenderConfig;
import com.example.demo9.dto.MailDto;
import com.example.demo9.repository.MailRepository;
import com.example.demo9.service.MailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSendException;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class Demo9ApplicationTests {

    @MockBean
    MailSenderConfig mailSender;

    @Autowired
    MailRepository mailRepository;

    @Autowired
    MailServiceImpl mailService;

    @BeforeEach
    void clear() {
        mailRepository.clear();
    }


    @Test
    public void testCorrectSending() {
        MailDto mail = new MailDto();
        mail.setEmailTo(Collections.singletonList("correctAddress"));
        doNothing().when(mailSender).configAndSendMail(any());
        mailService.sendEmail(mail);

        List<MailDto> message = mailService.findBySend(true);
        assertThat(message.size()).isEqualTo(1);
        assertThat(message.get(0).getEmailTo()).isEqualTo(Collections.singletonList("correctAddress"));

    }

    @Test
    public void testIncorrectSending() {
        doThrow(MailSendException.class).when(mailSender).configAndSendMail(any());
        MailDto mail = new MailDto();
        mail.setEmailTo(Collections.singletonList("IncorrectAddress"));
        mailService.sendEmail(mail);
        List<MailDto> message = mailService.findBySend(false);
        assertThat(message.size()).isEqualTo(1);
        assertThat(message.get(0).getEmailTo()).isEqualTo(Collections.singletonList("IncorrectAddress"));

    }



}
