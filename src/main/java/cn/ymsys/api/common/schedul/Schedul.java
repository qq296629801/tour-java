package cn.ymsys.api.common.schedul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableScheduling
@Slf4j
public class Schedul {
    @PostConstruct
    public void environmentSelfTest() {

    }

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void execute() {
        try {
            System.err.println("1111111111111111111");
        } catch (Throwable e) {
            log.error("后台运行调度捕获未知异常", e);
        }
    }

}
