package com.example.demo9.service;

import com.example.demo9.dto.MailDto;

import java.util.List;


public interface MailService {

    void sendEmail(MailDto message);

    List<MailDto> findBySend(boolean b);
}
