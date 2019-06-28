package cn.ymsys.api.repository;

import cn.ymsys.api.common.exception.PortalException;
import cn.ymsys.api.common.util.PagerUtil;
import cn.ymsys.api.orm.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(User user) {
        mongoTemplate.save(user);
    }

    public User findByName(String name) {
        return null;
    }


    public List<User> findByRole(Integer roleId, PagerUtil pager) {
        try {
            PagerUtil.startPage(pager);
            Query query = new Query();
            query.addCriteria(where("roleId").is(roleId));
            query.addCriteria(where("status").is(0));
            query.with(new Sort(Sort.Direction.ASC, "createTime"));
            return mongoTemplate.find(query, User.class);
        } catch (Throwable e) {
            PagerUtil.clearPage(pager);
            throw new PortalException("Query node info failed.", e);
        }
    }
}
