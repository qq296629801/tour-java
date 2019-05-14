package cn.ymsys.api.controller;

import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.PhotoService;
import cn.ymsys.api.common.request.BuPhotoRequest;
import cn.ymsys.api.common.response.JsonResponse;

@RestController
@RequestMapping("/photo")
@Api2Doc(id = "photo", name = "图片", order = 9)
public class BuPhotoController {
    @Autowired
    private PhotoService photoService;

    @ApiComment("列表")
    @RequestMapping("/list")
    public JsonResponse list(BuPhotoRequest vo) {
        return JsonResponse.build(photoService.find());
    }

    @ApiComment("保存")
    @RequestMapping("/save")
    public JsonResponse save(BuPhotoRequest vo) {
        return JsonResponse.build(photoService.save(vo));
    }

    @ApiComment("更新")
    @RequestMapping("/update")
    public JsonResponse update(BuPhotoRequest vo) {
        return JsonResponse.build(photoService.update(vo));
    }

    @ApiComment("删除")
    @RequestMapping("/delete")
    public JsonResponse delete(BuPhotoRequest vo) {
        return JsonResponse.build(photoService.delete(vo));
    }
}
