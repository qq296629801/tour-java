package cn.ymsys.api.service;

import cn.ymsys.api.common.model.Basics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicsService {
    private Basics dao = new Basics();

    public List<Basics> findAll() {
        return dao.find("select * from basics");
    }
}
