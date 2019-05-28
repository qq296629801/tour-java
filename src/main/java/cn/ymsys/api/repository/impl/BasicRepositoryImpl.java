package cn.ymsys.api.repository.impl;

import cn.ymsys.api.common.util.MongoAutoidUtil;
import cn.ymsys.api.model.Basic;
import cn.ymsys.api.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasicRepositoryImpl implements BasicRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoAutoidUtil mongoAutoidUtil;


    @Override
    public Page<Basic> findAll(SpringDataWebProperties.Pageable pageable) {
        return null;
    }

    @Override
    public void add(Basic basic) {
        basic.setId(mongoAutoidUtil.getNextSequence(Basic.class.getSimpleName()));
        mongoTemplate.save(basic);
    }

    @Override
    public Iterable<Basic> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Basic> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Basic> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Basic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Basic> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Basic> findAll() {
        return mongoTemplate.findAll(Basic.class);
    }

    @Override
    public Iterable<Basic> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Basic entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Basic> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
