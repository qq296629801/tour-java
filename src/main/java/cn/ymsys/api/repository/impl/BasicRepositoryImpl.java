package cn.ymsys.api.repository.impl;

import cn.ymsys.api.model.Basic;
import cn.ymsys.api.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicRepositoryImpl implements BasicRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Basic> findALl() {
        return mongoTemplate.findAll(Basic.class);
    }

    @Override
    public void add(Basic basic) {
        mongoTemplate.save(basic);
    }
}
