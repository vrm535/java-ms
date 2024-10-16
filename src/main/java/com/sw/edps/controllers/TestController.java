package com.sw.edps.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.sw.edps.services.TestService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	//Environment variable
	@Value("${APP_NAME}")
    private String appName;

	//Secret
	@Value("${MY_VAULT_VALUE}")
    private String testValue;

	//This is a service class where logic is isolated
	@Autowired
	private TestService service;

    @GetMapping
	public String get() {
		logger.info("Calling get api in the test controller");
		return service.getProducts();
	}
}
