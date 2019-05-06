package cn.ymsys.play;

import com.terran4j.commons.api2doc.config.EnableApi2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jyd.common.model._JFinalDemoGenerator;
import com.jyd.common.model._MappingKit;

@ComponentScan("cn.ymsys.api.*")
@SpringBootApplication
@EnableAsync
@EnableApi2Doc
public class BlogApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(_JFinalDemoGenerator.getDataSource());
		_MappingKit.mapping(arp);
		arp.start();

	}

}
