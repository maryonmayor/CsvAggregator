package com.tools.aggregator.config;

import java.util.Properties;

import org.apache.camel.builder.RouteBuilder;

import com.tools.aggregator.proccessor.CSVCounterProcessor;
import com.tools.aggregator.proccessor.CSVSummationProcessor;

public class CsvAggregatorRoute extends RouteBuilder {
	
	private Properties properties;
	
	public CsvAggregatorRoute(Properties properties) {
		this.properties = properties;
	}

	@Override
	public void configure() throws Exception {
		
		from("file:"+properties.getProperty("csv1")+"?noop=true")
		.unmarshal()
		.csv()
		.log("----frm csv1 ----: ${header.CamelFileName}")
		//.to("log:com.tools.aggregator?level=INFO")
		.to("direct:foraggregate");

		from("file:"+properties.getProperty("csv2")+"?noop=true")
		.unmarshal()
		.csv()
		.log("----frm csv2 ----: ${header.CamelFileName}")
		//.to("log:com.tools.aggregator?level=INFO")
		.to("direct:foraggregate");

		from("direct:foraggregate")
		.aggregate(header("CamelFileName"), new CsvAggregatorStrategy())
		.completionTimeout(10000)
		.log("----aggregate----: ${header.CamelFileName}")
		//.to("log:com.tools.aggregator?level=INFO")
		.marshal()
		.csv()
		.to("file:"+properties.getProperty("aggregated"))
		.to("direct:addSummation");
		
		
		from("direct:addSummation")
		.unmarshal()
		.csv()
		.process(new CSVSummationProcessor())
		.log("----summation----: ${header.CamelFileName}")
		.marshal()
		.csv()
		.to("file:"+properties.getProperty("aggregated-with-summation"));
		
		from("file:"+properties.getProperty("aggregated"))
		.unmarshal()
		.csv()
		.process(new CSVCounterProcessor())
		.log("----counting-----: ${header.CamelFileName}")
		.marshal()
		.csv()
		.to("file:"+properties.getProperty("aggregated-counted"));

	}

}
