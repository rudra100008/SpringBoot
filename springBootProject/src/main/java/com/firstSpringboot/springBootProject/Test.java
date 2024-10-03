package com.firstSpringboot.springBootProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class Test {
	@RequestMapping("/test")
	public String firstHandler() {
		return "Testing........";
	}

}
