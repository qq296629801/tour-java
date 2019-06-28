package cn.ymsys.api.controller;

import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.orm.model.user.User;
import cn.ymsys.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public JsonResponse add(@RequestBody User user) {
        return JsonResponse.success(userService.save(user));
    }

    @DeleteMapping(name = "删除指定用户", value = "/{id}")
    public void delete(@PathVariable("id") Long id) {

    }

    @GetMapping(name = "查询单个用户", value = "{id}")
    public User getUser(@PathVariable("id") Long id) {
        return null; // TODO: 还未实现。
    }

}
