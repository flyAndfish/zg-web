package com.study.pengxin.aop;

import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.pengxin.bean.ConsultConfigArea;

public class ServiceAspect {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public void before(JoinPoint joinPoint) {
		
		System.out.println("进入before。。。。。。。。。");
		Object[] args = joinPoint.getArgs();

        for(Object o:args) {
        	System.out.println("before======"+o);
        }
        System.out.println("离开before。。。。。。。。。");
	}
	
	public void after(JoinPoint joinPoint) {
		
		System.out.println("进入after。。。。。。。。。");
		Object[] args = joinPoint.getArgs();
		System.out.println(joinPoint.getTarget());
		
        for(Object o:args) {
        	System.out.println("after======="+o);
        }
        System.out.println("离开after。。。。。。。。。");
	}
	
	//可以控制目标方法的调用，以及返回结果（返回结果必须和目标方法返回结果一致）
	public Object around(ProceedingJoinPoint joinPoint) {
		System.out.println("进入around。。。。。。。。。");
		Object[] args = joinPoint.getArgs();

        for(Object o:args) {
        	System.out.println("around======="+o);
        }
        
        try {
			List<ConsultConfigArea> list = (List<ConsultConfigArea>) joinPoint.proceed();
			
			return list;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("离开around。。。。。。。。。");
        return null;
	}
}
