package com.gdp.exam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gdp.exam.demo.interceptor.BeforeActionInterceptor;
import com.gdp.exam.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	// BeforeActionInterceptor 불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	// BeforeActionInterceptor 불러오기
	@Autowired	
	NeedLoginInterceptor needLoginInterceptor; 
	
	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/error").excludePathPatterns("/resource/**");
		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/user/article/write").addPathPatterns("/user/article/doWrite").addPathPatterns("/user/article/modifty").addPathPatterns("/user/article/doModify").addPathPatterns("/user/article/doDelete");
	}
	
	
}
