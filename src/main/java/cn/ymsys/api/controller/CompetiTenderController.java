package cn.ymsys.api.controller;

import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.CompetiTenderService;
import cn.ymsys.api.common.request.CompetiRequest;
import cn.ymsys.api.common.response.JsonResponse;

@RestController
@RequestMapping("/competi")
@Api2Doc(id = "competi", name = "竞标", order = 6)
public class CompetiTenderController {
    @Autowired
    private CompetiTenderService competiTenderService;

    @ApiComment("竞标用户")
    @RequestMapping("/tenderUsers")
    public JsonResponse tenderUsers(@RequestBody CompetiRequest vo) {
        return JsonResponse.build(competiTenderService.find(vo));
    }

    @ApiComment("列表")
    @RequestMapping("/list")
    public JsonResponse list(@RequestBody CompetiRequest vo) {
        return JsonResponse.build(competiTenderService.findByPage(vo));
    }

    @ApiComment("保存")
    @RequestMapping("/save")
    public JsonResponse save(@RequestBody CompetiRequest vo) {
        return JsonResponse.build(competiTenderService.save(vo));
    }

    @ApiComment("更新")
    @RequestMapping("/update")
    public JsonResponse update(@RequestBody CompetiRequest vo) {
        return JsonResponse.build(competiTenderService.update(vo));
    }

    @ApiComment("删除")
    @RequestMapping("/delete")
    public JsonResponse delete(@RequestBody CompetiRequest vo) {
        return JsonResponse.build(competiTenderService.delete(vo));
    }
}
