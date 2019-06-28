package cn.ymsys.api.service;

import cn.ymsys.api.orm.model.post.Post;
import cn.ymsys.api.repository.TrendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendsService {
    @Autowired
    private TrendsRepository trendsRepository;

    public Post add(Post trends) {
        return trendsRepository.save(trends);
    }


    public List<Post> query(Post trends) {
        return trendsRepository.query(trends);
    }
}
