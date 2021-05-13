package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AccessingDataMysqlApplication {

	private static final Logger logger = LoggerFactory.getLogger(AccessingDataMysqlApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(AccessingDataMysqlApplication.class, args);
		logger.info("Mysql url: " + ctx.getEnvironment().getProperty("spring.datasource.url"));
		logger.info("Start Application at port: "+ ctx.getEnvironment().getProperty("server.port"));
	}
}
