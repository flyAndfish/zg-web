package com.study.pengxin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.pengxin.bean.ConsultConfigArea;
import com.study.pengxin.service.CommonService;

@Controller
public class MyController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CommonService commonService;
	
	@RequestMapping("login")
	@ResponseBody
	public String login() {
		List<ConsultConfigArea> list = commonService.qryArea(null, null);
		
		logger.info("登錄成功。。。。");
		
		return list.get(0).getAreaCode();
	}
}
