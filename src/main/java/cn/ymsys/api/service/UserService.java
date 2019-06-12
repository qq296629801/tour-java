package cn.ymsys.api.service;

import cn.ymsys.api.common.exception.PortalException;
import cn.ymsys.api.common.response.user.UserResponse;
import cn.ymsys.api.common.util.DataUtil;
import cn.ymsys.api.model.User;
import cn.ymsys.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        if (DataUtil.isEmpty(user.getPhone())) {
            throw new PortalException("手机号不能为空");
        }
        if (DataUtil.isEmpty(user.getPassword())) {
            throw new PortalException("密码不能为空");
        }
        userRepository.save(user);
        return user;
    }

    public UserResponse findByRole(User user) {
        if (DataUtil.isNull(user.getRoleId())) {
            throw new PortalException("角色ID不能为空");
        }
        return new UserResponse(userRepository.findByRole(user.getRoleId(), user));
    }
}
