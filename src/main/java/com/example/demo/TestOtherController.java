package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pv on 19/2/18.
 */
@RestController
public class TestOtherController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		return "hello";
	}
}
