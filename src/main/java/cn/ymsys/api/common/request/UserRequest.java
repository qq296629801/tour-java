package cn.ymsys.api.common.request;

import cn.ymsys.api.common.util.PagerUtil;
import lombok.Data;

@Data
public class UserRequest extends PagerUtil {
    private Integer id;
}
