package cn.ymsys.api.controller.supplier;

import cn.ymsys.api.common.request.UserRequest;
import cn.ymsys.api.common.response.JsonResponse;
import cn.ymsys.api.common.response.PagerResponse;
import cn.ymsys.api.model.Basic;
import cn.ymsys.api.repository.BasicRepository;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import com.terran4j.commons.api2doc.annotations.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author mjy
 * @date 2019/05/23
 */
@RestController
@Api2Doc(id = "supplierHome", name = "首页", order = 11)
public class IndexSupplierController {

    @Autowired
    private BasicRepository basicRepository;

    @Api2Doc(order = 1)
    @ApiComment("首页")
    @ApiError(value = "abc", comment = "不知道打什么!")
    @PostMapping("/")
    public JsonResponse index(@RequestBody UserRequest vo) {
        Basic b = new Basic();
        b.setClounmName("dasdsa");
        b.setClunmTable("dasdasdasd");
        b.setKey("a");
        b.setValue("b");
        basicRepository.add(b);
        return PagerResponse.success(basicRepository.findAll(), vo);
    }

    @Api2Doc(order = 2)
    @ApiComment("首页轮播图")
    @GetMapping("/sliderWrapper")
    public JsonResponse sliderWrapper() {
        return JsonResponse.success("");
    }

    @Api2Doc(order = 3)
    @ApiComment("商家推荐")
    @GetMapping("/businessRecommend")
    public JsonResponse businessRecommend() {
        return JsonResponse.success("");
    }


    @Api2Doc(order = 4)
    @ApiComment("农庄推荐")
    @GetMapping("/farmRecommend")
    public JsonResponse farmRecommend() {
        return JsonResponse.success("");
    }

    @Api2Doc(order = 5)
    @ApiComment("会务推荐")
    @GetMapping("/servicesRecommend")
    public JsonResponse servicesRecommend() {
        return JsonResponse.success("");
    }

}
