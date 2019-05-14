package cn.ymsys.api.controller;

import cn.ymsys.api.service.PhotoService;
import cn.ymsys.api.service.TenderService;
import cn.ymsys.api.common.request.TenderRequest;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.common.response.JsonResponse;

@RestController
@RequestMapping("/tender")
@Api2Doc(id = "tender", name = "活动", order = 3)
public class TenderController {

    @Autowired
    private TenderService tenderService;
    @Autowired
    private PhotoService photoService;

    @ApiComment("列表")
    @RequestMapping("/list")
    public JsonResponse list(@RequestBody TenderRequest vo) {
        return JsonResponse.build(tenderService.findTenders(vo));
    }

    @ApiComment("详情")
    @RequestMapping("/single")
    public JsonResponse single(@RequestBody TenderRequest vo) {
        return JsonResponse.build(tenderService.findTender(vo));
    }

    @ApiComment("轮播")
    @RequestMapping("/photos")
    public JsonResponse photos(@RequestBody TenderRequest vo) {
        return JsonResponse.build(photoService.get("BuUTenderPhotoDaoImpl", vo.getId()));
    }

    @ApiComment("保存")
    @RequestMapping("/save")
    public JsonResponse save(@RequestBody TenderRequest vo) {
        return JsonResponse.build(tenderService.save(vo));
    }

    @ApiComment("更新")
    @RequestMapping("/update")
    public JsonResponse update(@RequestBody TenderRequest vo) {
        return JsonResponse.build(tenderService.update(vo));
    }

    @ApiComment("删除")
    @RequestMapping("/delete")
    public JsonResponse delete(@RequestBody TenderRequest vo) {
        return JsonResponse.build(tenderService.delete(vo));
    }
}
