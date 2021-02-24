package com.zhangyan.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务A的接口
 * @author zhonghuashishan
 *
 */
@RestController
public class ServiceAController {

	@RequestMapping(value = "/sayHello1/{name}",
			method = RequestMethod.GET)
	public String sayHello(@PathVariable("name") String name) {
		return "{'msg': 'hello, " + name + "'}";  
	}

}