package cn.ymsys.api.model.user;

import lombok.Data;

@Data
public class UserRole {
    private int userId;
    private User user;
    private Role role;
    private int roleId;
}
