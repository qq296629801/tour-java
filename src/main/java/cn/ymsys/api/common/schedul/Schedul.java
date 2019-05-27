package cn.ymsys.api.common.schedul;

import cn.ymsys.api.common.model.SysExecutionQueue;
import cn.ymsys.api.service.BaiduOrcService;
import cn.ymsys.api.service.JobQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class Schedul {
    @PostConstruct
    public void environment() {

    }

    @Autowired
    private BaiduOrcService baiduOrcService;

    @Autowired
    private JobQueueService queueService;


    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void execute() {
        try {
            //System.err.println("------------schedul begin----------------------");
            List<SysExecutionQueue> queueList = queueService.getQueues();
            for (SysExecutionQueue queue : queueList) {
                baiduOrcService.handleEvent("BusinessLicenseOCR");
            }
        } catch (Throwable e) {
            log.error("后台运行调度捕获未知异常", e);
        }
    }

}
