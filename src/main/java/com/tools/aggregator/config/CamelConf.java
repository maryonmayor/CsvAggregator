package com.tools.aggregator.config;

import java.util.Properties;

import javax.naming.NamingException;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConf {
	
	@Bean
	public CamelContext camelContext (JndiContext jndiContext, Properties properties) throws Exception {
		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		camelContext.addRoutes(new CsvAggregatorRoute(properties));
		//camelContext.addRoutes(new CsvAggregatorRoute());
		return camelContext;
	}
	
	@Bean
	public JndiContext jndiContext(Properties properties) throws Exception {
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("properties", properties);
		return jndiContext ;
	}
	

}
