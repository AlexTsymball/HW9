package com.example.demo9.service;

import com.example.demo9.config.MailSenderConfig;
import com.example.demo9.data.Mail;
import com.example.demo9.dto.MailAllDto;
import com.example.demo9.dto.MailDto;
import com.example.demo9.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

   private final MailRepository mailRepository;
    private final MailSenderConfig mailSender;

    @Value(value = "${spring.mail.username}")
    private String username;

    @Override
    public void sendEmail(MailDto mailDto) {
        String errorMsg = send(mailDto);
        MailAllDto message = convertToAllDto(mailDto, errorMsg);
        mailRepository.save(convertToData(message));

    }


    public String send(MailDto mailDto) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(username);
        mail.setTo(mailDto.getEmailTo().toArray(new String[0]));
        mail.setText(mailDto.getContent());
        mail.setSubject(mailDto.getSubject());
        try {
            mailSender.configAndSendMail(mail);
            return "Sent";
        } catch (MailException m) {
            return m.getMessage();
        }
    }

    @Override
    public List<MailDto> findBySend(boolean b) {
        return mailRepository.findBySend(b).stream()
                .map(this::convertToDto)
                .toList();
    }

    private MailDto convertToDto(Mail mail) {
        return MailDto.builder()
                .id(mail.getId())
                .content(mail.getContent())
                .emailTo(mail.getEmailTo())
                .subject(mail.getSubject())
                .build();
    }



    private Mail convertToData(MailAllDto mailAllDto) {
        return Mail.builder()
                .id(mailAllDto.getId())
                .content(mailAllDto.getContent())
                .errorMessage(mailAllDto.getErrorMessage())
                .send(mailAllDto.isSend())
                .subject(mailAllDto.getSubject())
                .emailTo(mailAllDto.getEmailTo())
                .build();
    }

    private MailAllDto convertToAllDto(MailDto message, String errorMsg) {

        if ("Sent".equals(errorMsg)) {
            return MailAllDto.builder()
                    .id(message.getId())
                    .errorMessage("")
                    .send(true)
                    .content(message.getContent())
                    .emailTo(message.getEmailTo())
                    .subject(message.getSubject())
                    .build();
        } else {
            return MailAllDto.builder()
                    .id(message.getId())
                    .errorMessage(errorMsg)
                    .send(false)
                    .content(message.getContent())
                    .emailTo(message.getEmailTo())
                    .subject(message.getSubject())
                    .build();
        }
    }

}
