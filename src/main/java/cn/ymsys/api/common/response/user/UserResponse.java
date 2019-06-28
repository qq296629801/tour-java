package cn.ymsys.api.common.response.user;

import cn.ymsys.api.orm.model.user.User;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class UserResponse extends JSONArray {
    public UserResponse(List<User> users) {
        super(users.isEmpty() ? 0 : users.size());
        JSONObject jsonObject = new JSONObject();
    }
}
