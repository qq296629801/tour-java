package cn.ymsys.api.service;

import cn.ymsys.api.model.Dynamics;
import cn.ymsys.api.repository.TrendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendsService {
    @Autowired
    private TrendsRepository trendsRepository;

    public Dynamics add(Dynamics trends) {
        return trendsRepository.save(trends);
    }


    public List<Dynamics> query(Dynamics trends) {
        return trendsRepository.query(trends);
    }
}
