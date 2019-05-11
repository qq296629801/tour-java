package cn.ymsys.api.controller;

import com.terran4j.commons.api2doc.annotations.Api2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.ymsys.api.service.FileService;
import cn.ymsys.api.common.response.JsonResponse;

@RestController
@RequestMapping("/api/public")
@Api2Doc(id = "public", name = "文件上传", order = 4)
public class PublicController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public JsonResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
        return JsonResponse.build(fileService.save(file));
    }

}
