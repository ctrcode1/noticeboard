package com.rsupport.pretest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value={"/", "/login"})
    public String login(Model model) {
        logger.info("login is called.");
        return "login";
    }
}
