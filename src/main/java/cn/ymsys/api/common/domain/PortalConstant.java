package cn.ymsys.api.common.domain;

/**
 * Portal 常量
 */
public class PortalConstant {

    // user缓存前缀
    public static final String USER_CACHE_PREFIX = "lambda.portal.cache.user.";
    // user角色缓存前缀
    public static final String USER_ROLE_CACHE_PREFIX = "lambda.portal.cache.user.role.";
    // user权限缓存前缀
    public static final String USER_PERMISSION_CACHE_PREFIX = "lambda.portal.cache.user.permission.";
    // user个性化配置前缀
    public static final String USER_CONFIG_CACHE_PREFIX = "lambda.portal.cache.user.config.";
    // token缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "lambda.portal.cache.token.";
    //在线用户名
    public static final String ACTIVE_USERNAMES_SET_PREFIX="lambda.portal.active.username.";
    //总访问
    public static final String TOTUAL_VISITS_PREFIX="lambda.portal.totual.visit.times.";
    //今日访问
    public static final String TODAY_VISITS_PREFIX="lambda.portal.today.visit.times.";
    //今日Ip
    public static final String TODAY_IP_PREFIX="lambda.portal.ip.";
    // 排序规则： descend 降序
    public static final String ORDER_DESC = "descend";
    // 排序规则： ascend 升序
    public static final String ORDER_ASC = "ascend";

    // 按钮
    public static final String TYPE_BUTTON = "1";
    // 菜单
    public static final String TYPE_MENU = "0";

    // 网络资源 Url
    public static final String MEIZU_WEATHER_URL = "http://aider.meizu.com/app/weather/listWeather";
    public static final String MRYW_TODAY_URL = "https://interface.meiriyiwen.com/article/today";
    public static final String MRYW_DAY_URL = "https://interface.meiriyiwen.com/article/day";
    public static final String TIME_MOVIE_HOT_URL = "https://api-m.mtime.cn/Showtime/LocationMovies.api";
    public static final String TIME_MOVIE_DETAIL_URL = "https://ticket-api-m.mtime.cn/movie/detail.api";
    public static final String TIME_MOVIE_COMING_URL = "https://api-m.mtime.cn/Movie/MovieComingNew.api";
    public static final String TIME_MOVIE_COMMENTS_URL = "https://ticket-api-m.mtime.cn/movie/hotComment.api";

}
