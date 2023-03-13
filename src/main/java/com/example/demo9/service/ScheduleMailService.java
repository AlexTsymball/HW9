package com.example.demo9.service;

import com.example.demo9.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleMailService {

    private final MailService mailService;

    @Scheduled(initialDelay = 1000L, fixedDelay = 300000L)
    void method() {
        List<MailDto> messages = mailService.findBySend(false);
        for (MailDto message : messages) {
             mailService.sendEmail(message);
        }
    }
}
