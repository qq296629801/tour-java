package cn.ymsys.api.controller;

import cn.ymsys.api.service.PhotoService;
import cn.ymsys.api.service.TenderService;
import cn.ymsys.request.ProductRequest;
import cn.ymsys.request.TenderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.request.UserRequest;
import cn.ymsys.response.JsonResponse;

@RestController
@RequestMapping("/tender")
public class TenderController {

	@Autowired
	private TenderService tenderService;
	@Autowired
	private PhotoService photoService;

	@RequestMapping("/list")
	public JsonResponse list(@RequestBody TenderRequest vo) {
		return JsonResponse.build(tenderService.findTenders(vo));
	}

	@RequestMapping("/single")
	public JsonResponse single(@RequestBody TenderRequest vo) {
		return JsonResponse.build(tenderService.findTender(vo));
	}

	@RequestMapping("/photos")
	public JsonResponse photos(@RequestBody TenderRequest vo) {
		return JsonResponse.build(photoService.get("BuUTenderPhotoDaoImpl", vo.getId()));
	}

	@RequestMapping("/save")
	public JsonResponse save(@RequestBody TenderRequest vo) {
		return JsonResponse.build(tenderService.save(vo));
	}

	@RequestMapping("/update")
	public JsonResponse update(@RequestBody TenderRequest vo) {
		return JsonResponse.build(tenderService.update(vo));
	}

	@RequestMapping("/delete")
	public JsonResponse delete(@RequestBody TenderRequest vo) {
		return JsonResponse.build(tenderService.delete(vo));
	}
}
