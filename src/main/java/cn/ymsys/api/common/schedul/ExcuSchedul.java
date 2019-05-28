package cn.ymsys.api.common.schedul;

import cn.ymsys.api.service.BaiduOrcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableScheduling
@Slf4j
public class ExcuSchedul {
    @PostConstruct
    public void environment() {

    }

    @Autowired
    private BaiduOrcService baiduOrcService;


    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void execute() {
        try {
            // baiduOrcService.handleEvent("BusinessLicenseOCR");
        } catch (Throwable e) {
            log.error("后台运行调度捕获未知异常", e);
        }
    }

}
