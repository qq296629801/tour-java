package cn.ymsys.api.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.*;
import java.net.URL;

@Slf4j
public class AddressUtil {

    private static final DbSearcher DB_SEARCHER = getDbSearcher();

    private static DbSearcher getDbSearcher() {

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL url = classLoader.getResource("ip2region/ip2region.db");
            if (url == null) {
                throw new IOException("Resource file not found: " + url.getPath());
            }

            byte[] dbBin = readInputStream(url.openStream());
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbBin);
            return searcher;
        } catch (Throwable e) {
            log.error("初始化地址数据库异常：{}", e);
            System.exit(-1);
            return null;
        }
    }

    public static String getCityInfo(String ip) {

        if (StringUtils.isNotEmpty(ip) && Util.isIpAddress(ip)) {
            try {
                DataBlock dataBlock = DB_SEARCHER.memorySearch(ip);
                return dataBlock != null ? dataBlock.getRegion() : "";
            } catch (Exception e) {
                log.warn("获取地址信息异常：{}", e);
            }
        }
        return "";
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {

        byte[] buffer = new byte[8 * 1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) throws Exception {

        System.out.println(getCityInfo("127.0.0.1"));
    }
}