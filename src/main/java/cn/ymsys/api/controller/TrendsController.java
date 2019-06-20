package cn.ymsys.api.controller;

import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.common.response.PagerResponse;
import cn.ymsys.api.model.Dynamics;
import cn.ymsys.api.service.TrendsService;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trends")
@Api2Doc(id = "trends", name = "动态", order = 1)
public class TrendsController {
    @Autowired
    private TrendsService trendsService;

    @Api2Doc(order = 1)
    @ApiComment("发布动态")
    @RequestMapping("/add")
    public JsonResponse add(@ApiComment("动态") @RequestBody Dynamics trends) {
        return JsonResponse.success(trendsService.add(trends));
    }

    @Api2Doc(order = 2)
    @ApiComment("动态列表")
    @RequestMapping("/query")
    public JsonResponse query(@ApiComment("动态") @RequestBody Dynamics trends) {
        return PagerResponse.success(trendsService.query(trends), trends);
    }
}
