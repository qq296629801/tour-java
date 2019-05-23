package cn.ymsys.api.controller.purchase;

import cn.ymsys.api.common.request.CompanyRequest;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api2Doc(id = "purchaseHome", name = "首页", order = 11)
@ApiComment(seeClass = CompanyRequest.class)
public class IndexController {

    
}
