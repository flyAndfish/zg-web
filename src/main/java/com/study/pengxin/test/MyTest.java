package com.study.pengxin.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.pengxin.service.CommonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/spring/applicationContext-service.xml")
public class MyTest {

	Logger logger = LoggerFactory.getLogger(getClass());
	    
    @Autowired
    CommonService service;
    @Test
    public void test1() {
    	Map map = new HashMap();
    	map.put("areaCode", "qww");
        System.out.println("结果======"+service.qryArea(null, null));
    }
}
