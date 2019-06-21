package cn.ymsys.api.controller;

import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public JsonResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
        return JsonResponse.success(fileService.save(file));
    }

}
