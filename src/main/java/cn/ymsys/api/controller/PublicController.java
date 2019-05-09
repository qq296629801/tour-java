package cn.ymsys.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.ymsys.api.service.FileService;
import cn.ymsys.common.response.JsonResponse;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	@Autowired
	private FileService fileService;

	@RequestMapping("/upload")
	public JsonResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
		return JsonResponse.build(fileService.save(file));
	}

}
