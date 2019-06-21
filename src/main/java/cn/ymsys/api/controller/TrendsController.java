package cn.ymsys.api.controller;

import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.common.response.PagerResponse;
import cn.ymsys.api.model.post.Post;
import cn.ymsys.api.service.TrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trends")
public class TrendsController {
    @Autowired
    private TrendsService trendsService;

    @RequestMapping("/add")
    public JsonResponse add(@RequestBody Post trends) {
        return JsonResponse.success(trendsService.add(trends));
    }

    @RequestMapping("/query")
    public JsonResponse query(@RequestBody Post trends) {
        return PagerResponse.success(trendsService.query(trends), trends);
    }
}
