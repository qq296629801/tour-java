package cn.ymsys.api.controller;

import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.api.service.PhotoService;
import cn.ymsys.api.service.ProductService;
import cn.ymsys.api.common.request.ProductRequest;
import cn.ymsys.api.common.response.JsonResponse;

@RestController
@RequestMapping("/product")
@Api2Doc(id = "product", name = "产品", order = 5)
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PhotoService photoService;

    @ApiComment("列表")
    @RequestMapping("/list")
    public JsonResponse list(@RequestBody ProductRequest vo) {
        return JsonResponse.build(productService.findProducts(vo));
    }

    @ApiComment("详情")
    @RequestMapping("/single")
    public JsonResponse single(@RequestBody ProductRequest vo) {
        return JsonResponse.build(productService.findProduct(vo));
    }

    @ApiComment("轮播")
    @RequestMapping("/photos")
    public JsonResponse photos(@RequestBody ProductRequest vo) {
        return JsonResponse.build(photoService.get("ProductPhotoDaoImpl", vo.getId()));
    }

    @ApiComment("保存")
    @RequestMapping("/save")
    public JsonResponse save(@RequestBody ProductRequest vo) {
        return JsonResponse.build(productService.save(vo));
    }

    @ApiComment("更新")
    @RequestMapping("/update")
    public JsonResponse update(@RequestBody ProductRequest vo) {
        return JsonResponse.build(productService.update(vo));
    }

    @ApiComment("删除")
    @RequestMapping("/delete")
    public JsonResponse delete(@RequestBody ProductRequest vo) {
        return JsonResponse.build(productService.delete(vo));
    }
}
