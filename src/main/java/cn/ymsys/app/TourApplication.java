package cn.ymsys.app;

import com.terran4j.commons.api2doc.config.EnableApi2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

@ComponentScan("cn.ymsys.api.*")
@SpringBootApplication
@EnableAsync
@EnableApi2Doc
public class TourApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourApplication.class, args);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(JFinalDemoGenerator.getDataSource());
        MappingKit.mapping(arp);
        arp.start();
    }

}
