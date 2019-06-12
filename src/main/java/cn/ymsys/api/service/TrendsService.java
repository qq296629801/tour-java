package cn.ymsys.api.service;

import cn.ymsys.api.model.Trends;
import cn.ymsys.api.repository.TrendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendsService {
    @Autowired
    private TrendsRepository trendsRepository;

    public Trends register(Trends trends) {
        return trendsRepository.save(trends);
    }


    public List<Trends> query(Trends trends) {
        return trendsRepository.query(trends);
    }
}
