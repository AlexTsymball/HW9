package com.example.demo9.controller;

import com.example.demo9.dto.MailWithoutIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PutMessageKafkaController {

    private final KafkaTemplate<String, MailWithoutIdDto> kafkaTemplate;


    @PostMapping("/mail")
    public ResponseEntity<String> getMessage(@RequestBody MailWithoutIdDto message) {
        kafkaTemplate.send("mail", message);
        return ResponseEntity.status(HttpStatus.OK).body("The message is uploaded to kafka");
    }
}
