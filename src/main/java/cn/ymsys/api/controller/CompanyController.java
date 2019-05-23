package cn.ymsys.api.controller;

import cn.ymsys.api.service.CompanyService;
import cn.ymsys.api.service.PhotoService;
import cn.ymsys.api.common.request.CompanyRequest;
import cn.ymsys.api.common.response.JsonResponse;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Api2Doc(id = "company", name = "公司", order = 7)
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping("/list")
    @Api2Doc(order = 1)
    @ApiComment("公司列表")
    public JsonResponse list(@RequestBody CompanyRequest vo) {
        return JsonResponse.build(companyService.findCompanys(vo));
    }

    @RequestMapping("/single")
    @Api2Doc(order = 2)
    @ApiComment("公司详情页面")
    public JsonResponse single(@RequestBody CompanyRequest vo) {
        return JsonResponse.build(companyService.findCompany(vo));
    }

    @RequestMapping("/photos")
    @Api2Doc(order = 3)
    @ApiComment("公司详情页面轮播图")
    public JsonResponse photos(@RequestBody CompanyRequest vo) {
        return JsonResponse.build(photoService.findPhotos(vo.getId(), "公司图片"));
    }

    @RequestMapping("/save")
    @Api2Doc(order = 4)
    @ApiComment("保存公司")
    public JsonResponse save(@RequestBody CompanyRequest vo) {
        return JsonResponse.build(companyService.save(vo));
    }

    @RequestMapping("/update")
    @Api2Doc(order = 5)
    @ApiComment("更新公司")
    public JsonResponse update(@RequestBody CompanyRequest vo) {
        return JsonResponse.build(companyService.update(vo));
    }

    @RequestMapping("/delete")
    @Api2Doc(order = 6)
    @ApiComment("刪除公司")
    public JsonResponse delete(@RequestBody CompanyRequest vo) {
        return JsonResponse.build(companyService.delete(vo));
    }
}
