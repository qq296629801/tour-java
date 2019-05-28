package cn.ymsys.api.repository;

import cn.ymsys.api.common.util.MongoAutoidUtil;
import cn.ymsys.api.common.util.PortalUtil;
import cn.ymsys.api.model.Basic;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BasicRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoAutoidUtil mongoAutoidUtil;

    public void add(Basic basic) {
        basic.setId(mongoAutoidUtil.getNextSequence(Basic.class));
        mongoTemplate.save(basic);
    }

    public List<Basic> findAll() {
        return mongoTemplate.findAll(Basic.class);
    }

}
