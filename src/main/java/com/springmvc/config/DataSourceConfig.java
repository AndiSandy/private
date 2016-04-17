package com.springmvc.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.alibaba.druid.pool.DruidDataSource;

import java.util.Properties;

/**
 *
 */
@Configuration
//加载资源文件
@PropertySource({"classpath:/config/properties/db.properties"})
public class DataSourceConfig {

	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.username}")
	String userName;
	@Value("${jdbc.password}")
	String passWord;

//	public Properties properties(){
//
//	}
	
	@Bean(name = "dataSource")
	public DruidDataSource druidDataSource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(url);
		//druidDataSource.setConnectProperties();
		return druidDataSource;
	}
}


