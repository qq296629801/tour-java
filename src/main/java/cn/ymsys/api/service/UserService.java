package cn.ymsys.api.service;

import cn.ymsys.api.orm.model.User;
import cn.ymsys.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return user;
    }
}
