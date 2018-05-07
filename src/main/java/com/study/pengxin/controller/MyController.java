package com.study.pengxin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.pengxin.bean.ConsultConfigArea;
import com.study.pengxin.service.CommonService;

import io.netty.handler.codec.http.HttpResponse;

@Controller
public class MyController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CommonService commonService;
	
	@RequestMapping("login")
	@ResponseBody
	public String login() {
		Map map = new HashMap();
    	map.put("areaCode", "qww");
		List<ConsultConfigArea> list = commonService.qryArea(map, null);
		
		logger.info("登錄成功。。。。");
		
		return list.get(0).getAreaCode();
	}
	@RequestMapping("chat")
	public String chat(@RequestParam("user1") String user1,@RequestParam("user2") String user2, Model model) {
		model.addAttribute("from", user1);
		model.addAttribute("to", user2);
		return "chat.jsp";
	}
	
}
