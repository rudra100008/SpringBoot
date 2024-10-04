package com.blogrestapi.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.blogrestapi.Entity.DatabaseSequence;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public long generateSequence(String sequenceName)
    {
        Query query =new Query(Criteria.where("_id").is(sequenceName));
        Update update=new Update().inc("seq", 1);
        DatabaseSequence counter=mongoTemplate .findAndModify(
            query, 
            update,
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            DatabaseSequence.class);
         return !Objects.isNull(counter)?counter.getSeq():1;   
    }
}
