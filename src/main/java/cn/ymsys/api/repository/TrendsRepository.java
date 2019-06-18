package cn.ymsys.api.repository;

import cn.ymsys.api.common.exception.PortalException;
import cn.ymsys.api.common.util.PagerUtil;
import cn.ymsys.api.model.Trends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class TrendsRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Trends save(Trends trends) {
        mongoTemplate.save(trends);
        return trends;
    }

    public List<Trends> query(Trends trends) {
        try {
            PagerUtil.startPage(trends);
            Query query = new Query();
            query.addCriteria(where("userId").is(trends.getUserId()));
            query.addCriteria(where("status").is(0));
            query.with(new Sort(Sort.Direction.ASC, "createTime"));
            return mongoTemplate.find(query, Trends.class);
        } catch (Throwable e) {
            PagerUtil.clearPage(trends);
            throw new PortalException("", e);
        }
    }
}