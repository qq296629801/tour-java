package cn.ymsys.api.controller;

import cn.ymsys.api.service.BaiduOrcService;
import cn.ymsys.api.service.PhotoService;
import cn.ymsys.common.request.CompanyRequest;
import cn.ymsys.common.response.JsonResponse;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import com.terran4j.commons.api2doc.annotations.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api2Doc(id = "home", name = "首页", order = 1)
@ApiComment(seeClass = CompanyRequest.class)
public class IndexController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private BaiduOrcService baiduOrcService;

    @Api2Doc(order = 1)
    @ApiComment("首页")
    @ApiError(value = "abc", comment = "不知道打什么!")
    @GetMapping("/")
    public JsonResponse index() {
        baiduOrcService.get("IdCardDaoImpl");
        return JsonResponse.build(photoService.get("ProductPhotoDaoImpl", 1));
    }

    @Api2Doc(order = 2)
    @ApiComment("首页轮播图")
    @GetMapping("/sliderWrapper")
    public JsonResponse sliderWrapper() {
        return JsonResponse.build("");
    }

    @Api2Doc(order = 3)
    @ApiComment("商家推荐")
    @GetMapping("/businessRecommend")
    public JsonResponse businessRecommend() {
        return JsonResponse.build("");
    }


    @Api2Doc(order = 4)
    @ApiComment("农庄推荐")
    @GetMapping("/farmRecommend")
    public JsonResponse farmRecommend() {
        return JsonResponse.build("");
    }

    @Api2Doc(order = 5)
    @ApiComment("会务推荐")
    @GetMapping("/servicesRecommend")
    public JsonResponse servicesRecommend() {
        return JsonResponse.build("");
    }

}
