package com.example.demo9.repository;

import com.example.demo9.data.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Repository
@RequiredArgsConstructor
public class MailRepository {

    private final MongoTemplate mongoTemplate;

    private static final String collection = "mail";

    public void clear() {
        mongoTemplate.remove(new Query(), collection);
    }

    public void save(Mail mail) {
        mongoTemplate.save(mail, collection);
    }


    public List<Mail> findBySend(boolean b) {
        Query mongoQuery = new Query();

            mongoQuery.addCriteria(where("send").is(b));

        return mongoTemplate.find(mongoQuery, Mail.class);
    }


}
