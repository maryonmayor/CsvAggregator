package com.tools.aggregator;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CsvAggregator {

	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = new AnnotationConfigApplicationContext("com.tools.aggregator");
		CamelContext camelContext = (CamelContext) appContext.getBean("camelContext");
		camelContext.start();
		
		while(true) {
			Thread.sleep(1000);
		}
	}

}
