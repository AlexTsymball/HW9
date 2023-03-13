package com.example.demo9.listener;

import com.example.demo9.dto.MailDto;
import com.example.demo9.dto.MailWithoutIdDto;
import com.example.demo9.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageReceivedListener {

    private final MailService mailService;

    @KafkaListener(topics = "mail", groupId = "group_id")
    public void messageReceive(MailWithoutIdDto message) {
        mailService.sendEmail(MailDto.builder()
                .subject(message.getSubject())
                .emailTo(message.getEmailTo())
                .content(message.getContent())
                .id(null)
                .build());

    }
}
