package com.mongodbConnect.Service;



import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodbConnect.Entity.DatabaseSequence;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoTemplate mongoTemplate;
    public long generateSequence(String seqName)
    {
        Query query=new Query(Criteria.where("_id").is(seqName));
        ////here "seq is the document in the mongodb with collection database_seq"
        Update update=new Update().inc("seq", 1);
        //here "seq is the document in the mongodb with collection database_seq"
        FindAndModifyOptions options=FindAndModifyOptions.options().returnNew(true).upsert(true);
        //returnNew:- this return the new id after the update
        //upsert:-if there is no document with "_id" and "seq" then mongodb will create one
        DatabaseSequence sequence=mongoTemplate.findAndModify(query, update,options,DatabaseSequence.class);
        return !Objects.isNull(sequence)?sequence.getSeq():1;
    }
}
