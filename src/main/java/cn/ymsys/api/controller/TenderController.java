package cn.ymsys.api.controller;

import cn.ymsys.api.service.PhotoService;
import cn.ymsys.api.service.TenderService;
import cn.ymsys.api.common.request.TenderRequest;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
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
