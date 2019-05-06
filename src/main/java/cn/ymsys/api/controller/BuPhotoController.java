package cn.ymsys.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.PhotoService;
import cn.ymsys.request.BuPhotoRequest;
import cn.ymsys.response.JsonResponse;

@RestController
@RequestMapping("/photo")
public class BuPhotoController {
	@Autowired
	private PhotoService photoService;

	@RequestMapping("/list")
	public JsonResponse list(BuPhotoRequest vo) {
		return JsonResponse.build(photoService.find());
	}

	@RequestMapping("/save")
	public JsonResponse save(BuPhotoRequest vo) {
		return JsonResponse.build(photoService.save(vo));
	}

	@RequestMapping("/update")
	public JsonResponse update(BuPhotoRequest vo) {
		return JsonResponse.build(photoService.update(vo));
	}

	@RequestMapping("/delete")
	public JsonResponse delete(BuPhotoRequest vo) {
		return JsonResponse.build(photoService.delete(vo));
	}
}
