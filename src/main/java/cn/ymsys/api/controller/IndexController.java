package cn.ymsys.api.controller;

import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public JsonResponse index() {
        return JsonResponse.success("");
    }

}
