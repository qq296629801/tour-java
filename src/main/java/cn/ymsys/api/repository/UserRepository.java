package cn.ymsys.api.repository;

import cn.ymsys.api.common.util.MongoAutoidUtil;
import cn.ymsys.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoAutoidUtil mongoAutoidUtil;

    public User findByName(String userName) {
        return null;
    }
}
