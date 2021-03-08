package com.zhangyan.provider.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath:bytetcc-supports-springcloud.xml" })
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zhangyan.provider")
public class SampleProviderMain {

	public static void main(String[] args) {
		SpringApplication.run(SampleProviderMain.class, args);
//		new SpringApplicationBuilder(SampleProviderMain.class).run(args);
		System.out.println("springcloud-sample-provider started!");
	}

}