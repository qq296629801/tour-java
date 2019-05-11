package cn.ymsys.api.controller;

import cn.ymsys.api.common.request.CompanyRequest;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.AuthentiService;
import cn.ymsys.api.common.request.AuthentiRequest;
import cn.ymsys.api.common.response.JsonResponse;

/**
 * @author mjy
 */
@RestController
@RequestMapping("/auth")
@Api2Doc(id = "auth", name = "认证", order = 8)
public class AuthentiController {
    @Autowired
    private AuthentiService authentiService;

    @Api2Doc(order = 1)
    @ApiComment("认证列表")
    @RequestMapping("/list")
    public JsonResponse list(@RequestBody AuthentiRequest vo) {
        return JsonResponse.build(authentiService.findByPage(vo));
    }

    @RequestMapping("/save")
    public JsonResponse save(@RequestBody AuthentiRequest vo) {
        return JsonResponse.build(authentiService.save(vo));
    }

    @RequestMapping("/update")
    public JsonResponse update(@RequestBody AuthentiRequest vo) {
        return JsonResponse.build(authentiService.update(vo));
    }

    @RequestMapping("/delete")
    public JsonResponse delete(@RequestBody AuthentiRequest vo) {
        return JsonResponse.build(authentiService.delete(vo));
    }

    @RequestMapping("/userAuthentis")
    public JsonResponse userAuthentis(@RequestBody AuthentiRequest vo) {
        return JsonResponse.build(authentiService.find(vo));
    }

}
