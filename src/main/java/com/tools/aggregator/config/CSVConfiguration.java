package com.tools.aggregator.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CSVConfiguration {
	
	@Bean
	public Properties properties() throws IOException {
		InputStream input = new FileInputStream("config.properties");
		Properties properties = new Properties();
		properties.load(input);
		System.out.println(properties.getProperty("aggregated"));
		return properties ;
	}

}
