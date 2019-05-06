package cn.ymsys.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.AdService;
import cn.ymsys.request.SysAdRequest;
import cn.ymsys.response.JsonResponse;

@RestController
@RequestMapping("/ad")
public class AdController {
	@Autowired
	private AdService adService;

	@RequestMapping("/list")
	public JsonResponse list(SysAdRequest vo) {
		return JsonResponse.build(adService.find(vo));
	}

	@RequestMapping("/save")
	public JsonResponse save(SysAdRequest vo) {
		return JsonResponse.build(adService.save(vo));
	}

	@RequestMapping("/upate")
	public JsonResponse update(SysAdRequest vo) {
		return JsonResponse.build(adService.update(vo));
	}

	@RequestMapping("/delete")
	public JsonResponse delete(SysAdRequest vo) {
		return JsonResponse.build(adService.delete(vo));
	}
}
