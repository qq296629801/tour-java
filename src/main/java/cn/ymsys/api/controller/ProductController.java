package cn.ymsys.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.PhotoService;
import cn.ymsys.api.service.ProductService;
import cn.ymsys.common.request.ProductRequest;
import cn.ymsys.common.response.JsonResponse;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private PhotoService photoService;

	@RequestMapping("/list")
	public JsonResponse list(@RequestBody ProductRequest vo) {
		return JsonResponse.build(productService.findProducts(vo));
	}

	@RequestMapping("/single")
	public JsonResponse single(@RequestBody ProductRequest vo) {
		return JsonResponse.build(productService.findProduct(vo));
	}

	@RequestMapping("/photos")
	public JsonResponse photos(@RequestBody ProductRequest vo) {
		return JsonResponse.build(photoService.get("ProductPhotoDaoImpl", vo.getId()));
	}

	@RequestMapping("/save")
	public JsonResponse save(@RequestBody ProductRequest vo) {
		return JsonResponse.build(productService.save(vo));
	}

	@RequestMapping("/update")
	public JsonResponse update(@RequestBody ProductRequest vo) {
		return JsonResponse.build(productService.update(vo));
	}

	@RequestMapping("/delete")
	public JsonResponse delete(@RequestBody ProductRequest vo) {
		return JsonResponse.build(productService.delete(vo));
	}
}
