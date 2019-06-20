package cn.ymsys.api.model.user;

import lombok.Data;

@Data
public class RoleMenu {
    private int roleId;
    private int menuId;
    private Role role;
    private Menu menu;
}
