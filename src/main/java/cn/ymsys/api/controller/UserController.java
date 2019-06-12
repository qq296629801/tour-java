package cn.ymsys.api.controller;

import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.common.response.user.UserResponse;
import cn.ymsys.api.model.User;
import cn.ymsys.api.service.UserService;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import com.terran4j.commons.api2doc.annotations.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api2Doc(id = "user", name = "用户", order = 1)
public class UserController {

    @Autowired
    private UserService userService;

    @Api2Doc(order = 1)
    @ApiComment("添加一个新的用户。")
    @ApiError(value = "1000", comment = "此用户已经存在！")
    @RequestMapping("/add")
    public JsonResponse add(@ApiComment("用户对象") @RequestBody User user) {
        return JsonResponse.success(userService.save(user));
    }

    @Api2Doc(order = 2)
    @ApiComment("根据用户id，删除指定的用户")
    @ApiError(value = "user.not.found", comment = "此用户不存在！")
    @ApiError(value = "admin.cant.delete", comment = "不允许删除管理员用户！")
    @DeleteMapping(name = "删除指定用户", value = "/{id}")
    public void delete(@PathVariable("id") Long id) {

    }

    @Api2Doc(order = 3)
    @ApiComment("根据用户id，查询此用户的信息")
    @ApiError(value = "user.not.found", comment = "此用户不存在！")
    @GetMapping(name = "查询单个用户", value = "{id}")
    public User getUser(@PathVariable("id") Long id) {
        return null; // TODO: 还未实现。
    }

    @Api2Doc(order = 4)
    @ApiComment("查询所有用户，按注册时间进行排序。")
    @RequestMapping("/querys")
    public JsonResponse getUsers(@ApiComment("用户对象") @RequestBody User user) {
        return JsonResponse.success(userService.findByRole(user));
    }
}
