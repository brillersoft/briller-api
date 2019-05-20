package com.briller.acess.configs;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class ServicesConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * create scheduler factory
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {

		// SchedulerJobFactory jobFactory = new SchedulerJobFactory();
		// jobFactory.setApplicationContext(applicationContext);

		Properties properties = new Properties();
		// properties.putAll(quartzProperties.getProperties());

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		// factory.setJobFactory(jobFactory);
		return factory;
	}

}
