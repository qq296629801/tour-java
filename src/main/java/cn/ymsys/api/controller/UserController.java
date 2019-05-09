package cn.ymsys.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.UserService;
import cn.ymsys.common.request.UserRequest;
import cn.ymsys.common.response.JsonResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public JsonResponse index(@RequestBody UserRequest vo) {
		return JsonResponse.build(userService.findUsers(vo));
	}

	@RequestMapping("/findUser")
	public JsonResponse findUuid(@RequestBody UserRequest vo) {
		return JsonResponse.build(userService.findByUUid(vo.getOpenId()));
	}

	@RequestMapping("/save")
	public JsonResponse save(@RequestBody UserRequest vo) {
		return JsonResponse.build(userService.save(vo));
	}

	@RequestMapping("/update")
	public JsonResponse update(@RequestBody UserRequest vo) {
		return JsonResponse.build(userService.update(vo));
	}

	@RequestMapping("/delete")
	public JsonResponse delete(@RequestBody UserRequest vo) {
		return JsonResponse.build(userService.delete(vo));
	}
}
