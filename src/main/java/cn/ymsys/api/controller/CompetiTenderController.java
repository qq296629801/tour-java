package cn.ymsys.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.CompetiTenderService;
import cn.ymsys.api.common.request.CompetiRequest;
import cn.ymsys.api.common.response.JsonResponse;

@RestController
@RequestMapping("/competi")
public class CompetiTenderController {
	@Autowired
	private CompetiTenderService competiTenderService;

	@RequestMapping("/tenderUsers")
	public JsonResponse tenderUsers(@RequestBody CompetiRequest vo) {
		return JsonResponse.build(competiTenderService.find(vo));
	}

	@RequestMapping("/list")
	public JsonResponse list(@RequestBody CompetiRequest vo) {
		return JsonResponse.build(competiTenderService.findByPage(vo));
	}

	@RequestMapping("/save")
	public JsonResponse save(@RequestBody CompetiRequest vo) {
		return JsonResponse.build(competiTenderService.save(vo));
	}

	@RequestMapping("/update")
	public JsonResponse update(@RequestBody CompetiRequest vo) {
		return JsonResponse.build(competiTenderService.update(vo));
	}

	@RequestMapping("/delete")
	public JsonResponse delete(@RequestBody CompetiRequest vo) {
		return JsonResponse.build(competiTenderService.delete(vo));
	}
}
