package com.gdp.exam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gdp.exam.demo.interceptor.BeforeActionInterceptor;
import com.gdp.exam.demo.interceptor.NeedLoginInterceptor;
import com.gdp.exam.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	// BeforeActionInterceptor 불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	@Autowired	
	NeedLoginInterceptor needLoginInterceptor; 

	@Autowired	
	NeedLogoutInterceptor needLogoutInterceptor; 
	
	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration ir;
		ir = registry.addInterceptor(beforeActionInterceptor);
		ir.addPathPatterns("/**");
		ir.excludePathPatterns("/error");
		ir.excludePathPatterns("/resource/**");
		
		// 로그인
		ir = registry.addInterceptor(needLoginInterceptor);
		
		// member
		ir.addPathPatterns("/user/member/myPage");
		ir.addPathPatterns("/user/member/checkPassword");
		ir.addPathPatterns("/user/member/doCheckPassword");
		ir.addPathPatterns("/user/member/modify");
		ir.addPathPatterns("/user/member/doModify");
		ir.addPathPatterns("/user/member/doDelete");
		
		// article
		ir.addPathPatterns("/user/article/write");
		ir.addPathPatterns("/user/article/doWrite");
		ir.addPathPatterns("/user/article/modifty");
		ir.addPathPatterns("/user/article/doModify");
		ir.addPathPatterns("/user/article/doDelete");
		
		// reply
		ir.addPathPatterns("/user/reply/write");
		ir.addPathPatterns("/user/reply/doWrite");
		ir.addPathPatterns("/user/reply/modifty");
		ir.addPathPatterns("/user/reply/doModify");
		ir.addPathPatterns("/user/reply/doDelete");
		
		// reactionPoint
		ir.addPathPatterns("/user/reactionPoint/doGoodReaction");
		ir.addPathPatterns("/user/reactionPoint/doBadReaction");
		ir.addPathPatterns("/user/reactionPoint/modifty");
		ir.addPathPatterns("/user/reactionPoint/doCancelGoodReaction");
		ir.addPathPatterns("/user/reactionPoint/doCancelBadReaction");
		
		
		ir = registry.addInterceptor(needLogoutInterceptor);
		
		// 로그아웃
		ir.addPathPatterns("/user/member/join");
		ir.addPathPatterns("/user/member/doJoin");
		ir.addPathPatterns("/user/member/login");
		ir.addPathPatterns("/user/member/dologin");
		ir.addPathPatterns("/user/member/findLoginId");
		ir.addPathPatterns("/user/member/doLoginId");
		ir.addPathPatterns("/user/member/findLoginPw");
		ir.addPathPatterns("/user/member/doLoginPw");
	}
	
	
}
