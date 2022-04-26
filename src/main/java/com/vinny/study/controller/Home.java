package com.vinny.study.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * <p></p>
 *
 * @author Vinny
 * @version $Id: Home, v1.0 2020/3/19 10:17 Vinny Exp $
 */
@Controller
public class Home {

	@RequestMapping("/ok.html")
	public void ok1(HttpServletRequest req, HttpServletResponse resp) {
		output(req, resp, "ok");
	}

	@RequestMapping("/ok")
	public void ok2(HttpServletRequest req, HttpServletResponse resp) {
		output(req, resp, "ok");
	}

	@RequestMapping("/")
	public void index1(HttpServletRequest req, HttpServletResponse resp) {
		output(req, resp, "Welcome to you!");
	}

	@RequestMapping("/index")
	public void index2(HttpServletRequest req, HttpServletResponse resp) {
		output(req, resp, "Welcome to you!");
	}

	private void output(HttpServletRequest req, HttpServletResponse resp, String message) {
		try (PrintWriter pw = resp.getWriter()) {
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			pw.write(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}