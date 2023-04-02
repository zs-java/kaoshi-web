package com.xzcoder.kaoshi.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BaseController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
public class BaseController {

	@RequestMapping("/welcome.html")
	public String welcomeHtml() throws Exception {
		return "admin/welcome";
	}

}
