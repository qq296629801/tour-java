package cn.ymsys.api.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class MongoAutoidUtil {
    @Autowired
    MongoTemplate mongoTemplate;

    public int getNextSequence(Class clazz) {
        MongoSequence seq = mongoTemplate.findAndModify(
                query(where("_id").is(clazz.getSimpleName())),
                new Update().inc("seq", 1),
                options().upsert(true).returnNew(true),
                MongoSequence.class);
        return seq.getSeq();
    }
}