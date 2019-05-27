package cn.ymsys.api.controller;

import cn.ymsys.api.common.request.SysAdRequest;
import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.service.AdService;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjy
 * @date 2019/05/23
 */
@RestController
@RequestMapping("/ad")
@Api2Doc(id = "ad", name = "广告", order = 10)
public class AdController {
    @Autowired
    private AdService adService;

    @ApiComment("列表")
    @RequestMapping("/list")
    public JsonResponse list(SysAdRequest vo) {
        return JsonResponse.build(adService.find(vo));
    }

    @ApiComment("保存")
    @RequestMapping("/save")
    public JsonResponse save(SysAdRequest vo) {
        return JsonResponse.build(adService.save(vo));
    }

    @ApiComment("更新")
    @RequestMapping("/upate")
    public JsonResponse update(SysAdRequest vo) {
        return JsonResponse.build(adService.update(vo));
    }

    @ApiComment("删除")
    @RequestMapping("/delete")
    public JsonResponse delete(SysAdRequest vo) {
        return JsonResponse.build(adService.delete(vo));
    }
}
