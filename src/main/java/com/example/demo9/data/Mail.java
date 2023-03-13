package com.example.demo9.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("mail")
public class Mail {
    @Id
    private String id;

    private String subject;

    private String content;

    private List<String> emailTo;

    private boolean send;

    private String errorMessage;
}
