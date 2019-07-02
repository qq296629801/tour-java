package cn.ymsys.api.service;

import cn.ymsys.api.orm.mapper.UserMapper;
import cn.ymsys.api.orm.model.User;
import cn.ymsys.api.orm.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String username) {
        return null;
    }

    public int find(){
        return userMapper.deleteByPrimaryKey(1L);
    }


}
