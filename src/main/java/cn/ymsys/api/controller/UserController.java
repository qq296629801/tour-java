package cn.ymsys.api.controller;

import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.UserService;
import cn.ymsys.api.common.request.UserRequest;
import cn.ymsys.api.common.response.JsonResponse;

@RestController
@RequestMapping("/user")
@Api2Doc(id = "user", name = "用户", order = 2)
public class UserController {
    @Autowired
    private UserService userService;

    @ApiComment("列表")
    @RequestMapping("/list")
    public JsonResponse index(@RequestBody UserRequest vo) {
        return JsonResponse.build(userService.findUsers(vo));
    }

    @ApiComment("查找用戶")
    @RequestMapping("/findUser")
    public JsonResponse findUuid(@RequestBody UserRequest vo) {
        return JsonResponse.build(userService.findByUUid(vo.getOpenId()));
    }

    @ApiComment("保存")
    @RequestMapping("/save")
    public JsonResponse save(@RequestBody UserRequest vo) {
        return JsonResponse.build(userService.save(vo));
    }

    @ApiComment("更新")
    @RequestMapping("/update")
    public JsonResponse update(@RequestBody UserRequest vo) {
        return JsonResponse.build(userService.update(vo));
    }

    @ApiComment("删除")
    @RequestMapping("/delete")
    public JsonResponse delete(@RequestBody UserRequest vo) {
        return JsonResponse.build(userService.delete(vo));
    }
}
